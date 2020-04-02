package com.wqy.modules.shopping.mapper;

import com.wqy.modules.shopping.entity.ShMember;

import java.math.BigDecimal;

/**
 * 分销逻辑，对应接口
 */
public interface ShDistributionMapper {
  //直推方法 ,返回高级会员注册金额30%

     void getPushPrice(ShMember shMember,BigDecimal inputPrice,int rate);

     //购买产品， 其上级以及上上级均可获得分红 ,自己会获得积分
    void byGoods(ShMember shMember,BigDecimal price);

    //积分提现
    boolean integralToCash(ShMember shMember,int price);

    //积分兑换
    boolean integralChange(ShMember shMember,BigDecimal goodsPrice);

}
