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
    @Autowired
    private ShMoneyMapper shMoneyMapper;
    @Autowired
    private ShCartMapper shCartMapper;
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
        //已经生成了一个订单 返回给前端填写剩下的数
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
        shCartMapper.insert(shCart);
        return shOrder;
    }

    @Override
    public Boolean updateShOrder(ShOrder shOrder) {
        //这里是正式下单或者修改订单 都需要保存订单
        ShMember shMember = shMemberMapper.selectById(shOrder.getMemberId());
        if (shOrder.getPayStatus()==1) {
            //已经下单支付这是增加物流之类的 此处要增加返还积分及其其他的上积分情况
            if (shMember.getLvVip()==0){
                //1.不是会员直接返还积分
                BigDecimal totalPrice = shOrder.getTotalPrice();
                BigDecimal subtract = totalPrice.divide(new BigDecimal(0.7),BigDecimal.ROUND_HALF_UP).subtract(totalPrice);//这就是需要的积分
                int i = subtract.intValue();//这是需要的积分
                shMember.setIntegral(shMember.getIntegral()-i);
                shMemberMapper.updateById(shMember);//这里改变了用户签到积分！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                shOrderMapper.insert(shOrder);//保存订单！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                return true;
            }
            //2.购买够10次了 清除所有连锁
            if (shMember.getIntegralChangeRate()>10){
                shMember.setParentId("");
                shMember.setIntegralChangeRate(shMember.getIntegralChangeRate()+1);//购买次数+1
                shMemberMapper.updateById(shMember);//保存用户下单数据 这里改变了用户购买次数！！！！！！！！！！！！！！！！！！！！！！！！！！！
                shOrderMapper.insert(shOrder);//保存订单！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                return true;
            }
            //3.没有购买够10次进行分红
            if (shMember.getIntegralChangeRate()<=10){
                //提出三层分红method
                shareMoney(shOrder);//三层分红完毕
                shMember.setIntegralChangeRate(shMember.getIntegralChangeRate()+1);
                shMemberMapper.updateById(shMember);//保存用户下单数据 这里改变了用户购买次数！！！！！！！！！！！！！！！！！！！！！！！！！！！
                shOrderMapper.insert(shOrder);//保存数据
                return true;
            }
            return false;
        }else if(shOrder.getPayStatus()==2){
            //钱包钱够 不通过微信支付
            if (shMember.getIntegralChangeRate()>10){
                shMember.setParentId("");
                shMember.setIntegralChangeRate(shMember.getIntegralChangeRate()+1);//购买次数+1
                BigDecimal totalPrice = shOrder.getTotalPrice();
                ShMoney shMoney = shMoneyMapper.selectById(shMember.getId());
                shMoney.setAmount(shMoney.getAmount().subtract(totalPrice));//这里对用户钱包进行了更改！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                shMoneyMapper.updateById(shMoney);
                shMemberMapper.updateById(shMember);//保存用户下单数据 这里改变了用户购买次数！！！！！！！！！！！！！！！！！！！！！！！！！！！
                shOrderMapper.insert(shOrder);//保存订单！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                return true;
            }
            //3.没有购买够10次进行分红
            if (shMember.getIntegralChangeRate()<=10){
                //提出三层分红method
                shareMoney(shOrder);//三层分红完毕
                shMember.setIntegralChangeRate(shMember.getIntegralChangeRate()+1);
                BigDecimal totalPrice = shOrder.getTotalPrice();
                ShMoney shMoney = shMoneyMapper.selectById(shMember.getId());
                shMoney.setAmount(shMoney.getAmount().subtract(totalPrice));//这里对用户钱包进行了更改！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                shMoneyMapper.updateById(shMoney);
                shMemberMapper.updateById(shMember);//保存用户下单数据 这里改变了用户购买次数！！！！！！！！！！！！！！！！！！！！！！！！！！！
                shOrderMapper.insert(shOrder);//保存数据
                return true;
            }
        }
        else {
            //这是没给钱正式下单即修改订单 只是修改订单没有产生积分变化
            if (!updateResources(shOrder)) {
                return false;
            }
            if (shMember.getLvVip()==0){
                //1.不是会员直接返还积分
                BigDecimal totalPrice = shOrder.getTotalPrice();
                BigDecimal subtract = totalPrice.divide(new BigDecimal(0.7),BigDecimal.ROUND_HALF_UP);//这就是需要的积分
                subtract = subtract.multiply(new BigDecimal(0.3));
                int i = subtract.intValue();//这是需要的积分
                if (shMember.getIntegral()-i>0){
                    //积分够得可以购买
                    shOrderMapper.insert(shOrder);
                    return true;
                }else {
                    //积分不够
                    return false;
                }
            }
            shOrderMapper.insert(shOrder);
            return true;
        }
        return false;
    }

    /**
     * 库存管理
     * **/
    private Boolean updateResources(ShOrder shOrder) {
        String memberId = shOrder.getMemberId();//获取会员ID
        ShCart shCart = shCartMapper.selectByMemberId(memberId);
        String goodsId = shCart.getGoodsId();
        Integer goodsNumber = shCart.getGoodsNumber();
        ShGoods shGoods = shGoodsMapper.selectById(goodsId);
        if (shGoods.getGoodsNumber()-goodsNumber>=0) {
            shGoods.setGoodsNumber(shGoods.getGoodsNumber()-goodsNumber);
            shGoodsMapper.updateById(shGoods);
            return true;
        }
        return false;
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
        BigDecimal multiply = totalPrice.divide(new BigDecimal(0.7),BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(0.1));//这是一层分红10%

        //获取该用户的钱包
        ShMoney shMoney = shMoneyMapper.selectById(shMember1.getId());
        shMoney.setAmount(shMoney.getAmount().add(multiply));
        shMoneyMapper.updateById(shMoney);
        if (shMember1.getParentId().equals("")) {
            //没有父ID 直接不分了
            return true;
        }

        //这个地方是增加钱
        ShMember shMember2 = shMemberMapper.selectById(shMember1.getParentId());//二层用户
        BigDecimal multiply1 = totalPrice.divide(new BigDecimal(0.7),BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(0.05));//这是一层分红10%

        ShMoney shMoney1 = shMoneyMapper.selectById(shMember2.getId());
        shMoney1.setAmount(shMoney1.getAmount().add(multiply1));
        shMoneyMapper.updateById(shMoney1);
        if (shMember2.getParentId().equals("")) {
            //没有父ID 直接不分了
            return true;
        }
        //这个地方是增加钱
        ShMember shMember3 = shMemberMapper.selectById(shMember2.getParentId());//三层用户
        BigDecimal multiply2 = totalPrice.divide(new BigDecimal(0.7),BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(0.05));//这是一层分红10%
        ShMoney shMoney2 = shMoneyMapper.selectById(shMember3.getId());
        shMoney2.setAmount(shMoney2.getAmount().add(multiply2));
        shMoneyMapper.updateById(shMoney2);
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
