package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.configer.exception.BizException;
import com.wqy.wx.back.plus2.entity.TCart;
import com.wqy.wx.back.plus2.entity.TOrder;
import com.wqy.wx.back.plus2.entity.TOrderInfo;
import com.wqy.wx.back.plus2.entity.TProduct;
import com.wqy.wx.back.plus2.mapper.TOrderInfoMapper;
import com.wqy.wx.back.plus2.mapper.TOrderMapper;
import com.wqy.wx.back.plus2.mapper.TProductMapper;
import com.wqy.wx.back.plus2.service.ITOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单页面 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
@Primary
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

    @Autowired
    private TProductMapper tProductMapper;
    @Autowired
    private TOrderInfoMapper tOrderInfoMapper;
    @Autowired
    private TOrderMapper tOrderMapper;

    @Override
    public List<TOrder> getList(TOrder tOrder) {
        QueryWrapper<TOrder> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tOrder, query);
        return this.list(query);
    }

    @Override
    public Page<TOrder> getPage(TOrder tOrder, PageDTO pageDTO) {
        QueryWrapper<TOrder> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tOrder, query);
        Page<TOrder> page = new Page<>(pageDTO.getPageIndex(), pageDTO.getPageSize());
        return this.page(page, query);
    }

    @Override
    @Transactional
    public TOrder insertOrder(List<TCart> tCartList) throws Exception {
        //获取了所有的购物车信息即商品iD
        //判断下是不是垃圾数据
        if (tCartList.size() < 1) {
            return null;
        }
        //1.生成唯一的订单ID 此ID 赋予订单详情用于查询具体商品
        String orderUUID = UUIDUtils.getCharAndNumr();
        //2.生成一张订单表
        TOrder tOrder = new TOrder();
        tOrder.setOrderNumber(orderUUID);//设置唯一订单号
        tOrder.setMenberId(tCartList.get(0).getMenberId());//注入menberID
        tOrder.setPayStatus(0);
        tOrder.setSendStatus(0);
        tOrder.setAliOrderId(0l);
        //已经生成了一个订单 返回给前端填写剩下的数据
        //生成订单详情页面
        for (TCart tCart : tCartList) {
            //拉取每一个购物车生成订单详情
            String procuteId = tCart.getProcuteId();//获取商品ID 通过商品ID 查询商品详情填充详情页
            TProduct tProduct = tProductMapper.selectById(procuteId);
            //此处增加库存判定
            if (!updateNumber(tOrder)) {
                throw new BizException("库存异常");
            }
            //以上
            TOrderInfo tOrderInfo = new TOrderInfo();//填充详情页
            tOrderInfo.setOrderId(orderUUID);
            tOrderInfo.setProductId(procuteId);
            tOrderInfo.setProductNumber(tCart.getProcuteNumber());
            tOrderInfo.setReceivableAmount(tProduct.getPriceOld().multiply(new BigDecimal(tCart.getProcuteNumber())));//...........这尼玛有毒填入实际价格即原价
            tOrderInfo.setReceivedAmount(tProduct.getPriceNew().multiply(new BigDecimal(tCart.getProcuteNumber())));//填入实收金额
            tOrderInfo.setDiscountAmount(tProduct.getPriceOld().multiply(new BigDecimal(tCart.getProcuteNumber()))
                    .subtract(tProduct.getPriceNew().multiply(new BigDecimal(tCart.getProcuteNumber()))));//优惠金额
            tOrderInfoMapper.insert(tOrderInfo);//保存详情页
        }
        //保存订单页
        tOrderMapper.insert(tOrder);
        return tOrderMapper.selectByOrderNumber(tOrder.getOrderNumber());
    }

    /**
     * 订单修改及其后续商品数量操作
     * 生成订单时已经判定库存问题
     **/
    @Override
    public boolean updateByOrderId(TOrder tOrders) throws Exception {
        //主要实现库存修改
        if (tOrders.getPayStatus() > 0) {
            //已经付款 去库存
            String orderNumber = tOrders.getOrderNumber();//订单号
            List<TOrderInfo> tOrderInfoList = tOrderInfoMapper.searchByOrderNumber(orderNumber);//获取所有下单商品
            for (TOrderInfo tOrderInfo : tOrderInfoList) {
                if (!updateNumber(tOrders)) {
                    throw new BizException("库存异常");
                }
                String productId = tOrderInfo.getProductId();//获取商品ID
                Integer product_number = tOrderInfo.getProductNumber();//获取商品数量
                TProduct tProduct = tProductMapper.selectById(productId);
                Integer proNumber = tProduct.getProNumber();
                tProduct.setProNumber(proNumber - product_number);//去库存
                tProduct.setProOut(tProduct.getProOut() + product_number);//加销量
                tProductMapper.updateById(tProduct);//修改
            }
            tOrderMapper.updateById(tOrders);//修改订单表
        }
        //以上实现库存修改
        else {
            //未付款正常订单项填充直接改订单
            tOrderMapper.updateById(tOrders);
        }
        return true;
    }

    /**
     * 库存判断
     **/
    public Boolean updateNumber(TOrder tOrder) {
        List<TOrderInfo> tOrderInfoList = tOrderInfoMapper.searchByOrderNumber(tOrder.getOrderNumber());//获取所有下单
        for (TOrderInfo tOrderInfo : tOrderInfoList) {
            Integer proNumber = tProductMapper.selectById(tOrderInfo.getProductId()).getProNumber();
            Integer productNumber = tOrderInfo.getProductNumber();
            if ((proNumber - productNumber) < 0) {
                return false;
            }
        }
        return true;
    }
}
