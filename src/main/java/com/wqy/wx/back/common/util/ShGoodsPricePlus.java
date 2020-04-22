package com.wqy.wx.back.common.util;

import com.wqy.wx.back.plus3.entity.ShGoods;

import java.math.BigDecimal;
import java.util.List;

public class ShGoodsPricePlus {
    public static List<ShGoods> add(List<ShGoods> list){
        for (ShGoods shGoods : list) {
            BigDecimal goodsPrice = shGoods.getGoodsPrice();
            BigDecimal multiply = goodsPrice.multiply(new BigDecimal(0.3).setScale(2, BigDecimal.ROUND_HALF_UP));
            shGoods.setGoodsIntegral(multiply);
            shGoods.setGoodsPriceNew(goodsPrice.multiply(new BigDecimal(0.7).setScale(2, BigDecimal.ROUND_HALF_UP)));
        }
        return list;
    }
    public static ShGoods add(ShGoods shGoods){
            BigDecimal goodsPrice = shGoods.getGoodsPrice();
            BigDecimal multiply = goodsPrice.multiply(new BigDecimal(0.3).setScale(2, BigDecimal.ROUND_HALF_UP));
            shGoods.setGoodsIntegral(multiply);
            shGoods.setGoodsPriceNew(goodsPrice.multiply(new BigDecimal(0.7).setScale(2, BigDecimal.ROUND_HALF_UP)));
        return shGoods;
    }
}
