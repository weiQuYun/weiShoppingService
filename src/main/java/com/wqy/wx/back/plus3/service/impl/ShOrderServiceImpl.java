package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus3.entity.*;
import com.wqy.wx.back.plus3.mapper.*;
import com.wqy.wx.back.plus3.service.IShOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShOrderServiceImpl extends ServiceImpl<ShOrderMapper, ShOrder> implements IShOrderService {

    @Autowired
    private ShGoodsAttrMapper shGoodsAttrMapper;
    @Autowired
    private ShOrderMapper shOrderMapper;
    @Autowired
    private ShGoodsMapper shGoodsMapper;
    @Autowired
    private ShMemberMapper shMemberMapper;
    @Autowired
    private ShVipMapper shVipMapper;
    @Override
    public ShOrder insertShOrder(ShCart shCart) {
        //如果你要下订单就必须给我一个购物车对象 其中有 1. 什么商品 2. 是什么颜色的手机
        if (shCart.getGoodsAttrIds().equals("")) {
            return null;
        }
        if (shCart.getUserId().equals("")) {
            return null;
        }
        if (shCart.getGoodsId().equals("")) {
            return null;
        }
        if (shCart.getGoodsNumber().equals("")) {
            return null;
        }
//        所有属性已经存在 生成订单
//        1.生成唯一的订单ID 此ID 赋予订单详情用于查询具体商品
        String orderUUID = UUIDUtils.getCharAndNumr();
        //2.生成一张订单表
        ShOrder shOrder = new ShOrder();
        //设置唯一的订单号
        shOrder.setOrderId(orderUUID);
        //注入memberID
        shOrder.setMemberId(shCart.getUserId());
        //设置支付状态
        shOrder.setPayStatus(0);
        //设置发货状态
        shOrder.setSendStatus(0);
        //设置支付返回码状态
        shOrder.setAliOrderId("");
        //已经生成了一个订单 返回给前端填写剩下的数据
        //生成订单详情页面 已经保证所有商品均为商品属性表的值 所以直接调用商品属性表即可获取这个商品的价格及他
        String goodsAttrIds = shCart.getGoodsAttrIds();
        //1.如果不是会员 7折加上30%签到积分返还
        //2.是会员 7折加上 10 5 5 返还
        //3.是会员 10次购买 5折 打穿 连锁
        ShGoodsAttr shGoodsAttr = shGoodsAttrMapper.selectById(goodsAttrIds);
        BigDecimal multiply = shGoodsAttr.getAttrPrice().multiply(new BigDecimal(shCart.getGoodsNumber()));
        multiply = judgeMember(shMemberMapper.selectByid(shCart.getUserId()),multiply);//此处算出到底是多少钱
        shOrder.setTotalPrice(multiply);//这里就把总价算出来了
        //直接返回前端要求前端填写剩下的坑
        ShGoods shGoods = shGoodsMapper.selectById(shCart.getGoodsId());
        shOrder.setShGoods(shGoods);
        shOrder.setShGoodsAttr(shGoodsAttr);
        return shOrder;
    }

    @Override
    public String updateShOrder(ShOrder shOrder) {
        //这里是正式下单或者修改订单 都需要保存订单
        ShMember shMember = shMemberMapper.selectById(shOrder.getMemberId());
        if (shOrder.getAliOrderId().equals("")) {
            //已经下单支付这是增加物流之类的 此处要增加返还积分及其其他的上积分情况
            if (shMember.getLvVip()==0){
                //1.不是会员直接返还积分
                BigDecimal totalPrice = shOrder.getTotalPrice();
                BigDecimal subtract = totalPrice.divide(new BigDecimal(0.7)).subtract(totalPrice);//这就是需要的积分
                int i = subtract.intValue();//这是需要的积分
                shMember.setIntegral(shMember.getIntegral()-i);
                return "下单成功";
            }
            //2.购买够10次了 清除所有连锁
            if (shMember.getIntegralChangeRate()>10){
                shMember.setParentId("");
                shMember.setIntegralChangeRate(shMember.getIntegralChangeRate()+1);//购买次数+1
            }
            //3.没有购买够10次进行分红
            if (shMember.getIntegralChangeRate()<=10){
                //提出三层分红method
                shareMoney(shOrder);
            }
            return ";";


        }else {
            //这是没给钱正式下单即修改订单 只是修改订单没有产生积分变化
            if (shMember.getLvVip()==0){
                //1.不是会员直接返还积分
                BigDecimal totalPrice = shOrder.getTotalPrice();
                BigDecimal subtract = totalPrice.divide(new BigDecimal(0.7)).subtract(totalPrice);//这就是需要的积分
                int i = subtract.intValue();//这是需要的积分
                if (shMember.getIntegral()-i>0){
                    //积分够得可以购买
                    shOrderMapper.insert(shOrder);
                    return "订单生成成功";
                }else {
                    //积分不够
                    return "积分不够不能购买";
                }
            }
            shOrderMapper.insert(shOrder);
            return "订单生成成功";
        }
    }

    /**
     * 这个方法抽出用于3层分销向上三层分红
     * **/
    private Boolean shareMoney(ShOrder shOrder) {
        String memberId = shOrder.getMemberId();//获取用户ID
        ShMember shMember = shMemberMapper.selectById(memberId);//获取到用户
        String parentId = shMember.getParentId();
        if (parentId.equals("")) {
        //没有父ID 直接不分了
            return true;
        }
            ShMember shMember1 = shMemberMapper.selectById(parentId);//一层用户
        BigDecimal totalPrice = shOrder.getTotalPrice();
        BigDecimal multiply = totalPrice.divide(new BigDecimal(0.7)).multiply(new BigDecimal(0.1));//这是一层分红10%
        if (shMember1.getParentId().equals("")) {
            //没有父ID 直接不分了
            return true;
        }
        //这个地方是增加钱
        ShMember shMember2 = shMemberMapper.selectById(shMember1.getParentId());//二层用户
        BigDecimal multiply1 = totalPrice.divide(new BigDecimal(0.7)).multiply(new BigDecimal(0.05));//这是一层分红10%
        if (shMember2.getParentId().equals("")) {
            //没有父ID 直接不分了
            return true;
        }
        //这个地方是增加钱
        ShMember shMember3 = shMemberMapper.selectById(shMember2.getParentId());//三层用户
        BigDecimal multiply2 = totalPrice.divide(new BigDecimal(0.7)).multiply(new BigDecimal(0.05));//这是一层分红10%
        //这个地方是增加钱
        return true;
    }


    @Override
    public ShOrder selectByShOrderId(String id) {
        return shOrderMapper.selectByOrderId(id);
    }

    private BigDecimal judgeMember(ShMember shMember, BigDecimal multiply){
        //1.如果不是会员 7折 //加上30%签到积分返还这个在正式下单付款改变
        if (shMember.getLvVip()==0) {
            return multiply.multiply(new BigDecimal(0.7)).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
        //2.是会员 //10次购买 5折 打穿 连锁
        if (shMember.getIntegralChangeRate()>10){
            return multiply.multiply(new BigDecimal(0.5)).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
        //3.是会员 //7折加上 10 5 5 返还
        return multiply.multiply(new BigDecimal(0.7)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }



}

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
// 获取了所有的购物车信息即商品iD
//        //判断下是不是垃圾数据
//        if (list.size() < 1) {
//            return null;
//        }
//        //1.生成唯一的订单ID 此ID 赋予订单详情用于查询具体商品
//        String orderUUID = UUIDUtils.getCharAndNumr();
//        //2.生成一张订单表
//        ShOrder shOrder = new ShOrder();
//        //设置唯一的订单号
//        shOrder.setOrderId(orderUUID);
//        //注入memberID
//        shOrder.setMemberId(list.get(0).getUserId());
//        //设置支付状态
//        shOrder.setPayStatus(0);
//        //设置发货状态
//        shOrder.setSendStatus(0);
//        //设置支付返回码状态
//        shOrder.setAliOrderId("");
//        //已经生成了一个订单 返回给前端填写剩下的数据
//        //生成订单详情页面
//        for (ShCart shCart : list) {
//            //拉取每一个购物车生成订单详情
//            String procuteId = shCart.getGoodsId();
//            //这里必须要做一个判断商品是买的属性表中的商品还是商品表的商品
//            if (shCart.getGoodsAttrIds().equals("")) {
//                //如果是属性表的商品执行价格为属性表价格
//            } else {
//                //不是属性表商品执行商品表价格
//            }
////            //获取商品ID 通过商品ID 查询商品详情填充订单详情页
////
////            //此处增加库存判定
////            if (!updateNumber(tOrder)) {
////                throw new BizException("库存异常");
////            }
////            //以上
////            TOrderInfo tOrderInfo = new TOrderInfo();//填充详情页
////            tOrderInfo.setOrderId(orderUUID);
////            tOrderInfo.setProductId(procuteId);
////            tOrderInfo.setProductNumber(tCart.getProcuteNumber());
////            tOrderInfo.setReceivableAmount(tProduct.getPriceOld().multiply(new BigDecimal(tCart.getProcuteNumber())));//...........这尼玛有毒填入实际价格即原价
////            tOrderInfo.setReceivedAmount(tProduct.getPriceNew().multiply(new BigDecimal(tCart.getProcuteNumber())));//填入实收金额
////            tOrderInfo.setDiscountAmount(tProduct.getPriceOld().multiply(new BigDecimal(tCart.getProcuteNumber()))
////                    .subtract(tProduct.getPriceNew().multiply(new BigDecimal(tCart.getProcuteNumber()))));//优惠金额
////            tOrderInfoMapper.insert(tOrderInfo);//保存详情页
////        }
////        //保存订单页
////        tOrderMapper.insert(tOrder);
////        return tOrderMapper.selectByOrderNumber(tOrder.getOrderNumber());
////
//////        return null;
//        }
//        return null;
//    }
//}
