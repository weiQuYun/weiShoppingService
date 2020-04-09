package com.wqy.modules.shopping.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wqy.modules.Exception.BizException;
import com.wqy.modules.shopping.entity.ShCart;
import com.wqy.modules.shopping.entity.ShGoods;
import com.wqy.modules.shopping.entity.ShOrder;
import com.wqy.modules.shopping.entity.ShOrderGoods;
import com.wqy.modules.shopping.mapper.ShOrderGoodsMapper;
import com.wqy.modules.shopping.mapper.ShOrderMapper;
import com.wqy.modules.shopping.service.IShGoodsService;
import com.wqy.modules.shopping.service.IShOrderGoodsService;
import com.wqy.modules.shopping.service.IShOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
@Service
public class ShOrderServiceImpl extends ServiceImpl<ShOrderMapper, ShOrder> implements IShOrderService {
    @Autowired
    private IShGoodsService iShGoodsService;
    @Autowired
    private IShOrderGoodsService iShOrderGoodsService;
    @Autowired
    private ShOrderMapper shOrderMapper;
    @Autowired
    private ShOrderGoodsMapper shOrderGoodsMapper;
    @Override
    public ShOrder selectAll(List<ShCart> shCarts) throws Exception{
        //获取了所有购物车信息即商品id
        //判断下是不是垃圾数据
        if (shCarts.size()<1) {
            return null;
        }
        //1.生成唯一的订单ID 此ID 赋予订单详情用于查询具体商品
        UUID orderUUID = UUID.randomUUID();
        String substring = orderUUID.toString().substring(0, 16);
        //2.生成一张订单表
        ShOrder shOrder = new ShOrder();
        shOrder.setId(substring);//设置唯一订单号
        shOrder.setMemberId(shCarts.get(0).getUserId());//注入menberID
        shOrder.setPayStatus(0);
        shOrder.setSendStatus(0);
        shOrder.setAliOrderId("");
        //已经生成了一个订单 返回给前端填写剩下的数据
        //生成订单详情页面
        for (ShCart shCarts1 : shCarts) {
            //拉取每一个购物车生成订单详情
            String goodsId= shCarts1.getGoodsId();//获取商品ID 通过商品ID 查询商品详情填充详情页
            ShGoods shGoods = iShGoodsService.selectById(goodsId);
            //shGoods.get   商品属性Id
            //此处增加库存判定
            if (shGoods.getGoodsNumber()<shCarts1.getGoodsNumber()){
                throw new BizException("库存异常");
            }
            //以上

            ShOrderGoods shOrderGoods = new ShOrderGoods();
            //设置订单id
            shOrderGoods.setOrderId(substring);
            //设置商品id
            shOrderGoods.setGoodsId(goodsId);
            //设置商品个数
            shOrderGoods.setGoodsNumber(shCarts1.getGoodsNumber());
            //总价格
            shOrderGoods.setGoodsPrice(shGoods.getGoodsPrice().multiply(new BigDecimal(shCarts1.getGoodsNumber())));
            shOrderGoodsMapper.insert(shOrderGoods);//保存详情页
        }
        //保存订单页
        shOrder.setCreateTime(new Date());
        shOrder.setUpdateTime(new Date());
        shOrderMapper.insert(shOrder);
        return shOrderMapper.selectByOrderId(shOrder.getId());
    }
    /**
     * 订单修改及其后续商品数量操作
     * 生成订单时已经判定库存问题
     * **/
    @Override
    public boolean updateByOrderId(ShOrder shOrder) throws Exception {
        //主要实现库存修改
        if (shOrder.getPayStatus()>0) {
            //已经付款 去库存
            String orderNumber = shOrder.getOrderId();//订单号
            List<ShOrderGoods> shOrderGoods = shOrderGoodsMapper.selectByOrderId(orderNumber);//获取所有下单商品
            for (ShOrderGoods shOrderGoods1: shOrderGoods) {
                String goodsId = shOrderGoods1.getGoodsId();
                ShGoods shGoods = iShGoodsService.selectById(goodsId);
                if (shOrderGoods1.getGoodsNumber()<shGoods.getGoodsNumber()){
                    throw new BizException("库存异常");
                }
                Integer orderGoods1GoodsNumber = shOrderGoods1.getGoodsNumber();//获取订单商品数量
                Integer goodsNumber = shGoods.getGoodsNumber(); //获取库存数量
                shGoods.setGoodsNumber(goodsNumber-orderGoods1GoodsNumber);//去库存
                shGoods.setSales(shGoods.getSales()+orderGoods1GoodsNumber);//加销量
                iShGoodsService.updateGoods(shGoods);//修改
            }
            shOrderMapper.updateOrder(shOrder);//修改订单表
        }
        //以上实现库存修改
        else {
            //未付款正常订单项填充直接改订单
           shOrderMapper.updateOrder(shOrder);
        }
        return true;
    }

}
