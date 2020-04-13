package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.entity.ShGoodsAttr;

import java.util.List;

/**
 * <p>
 * 商品详情表用于商品页面的商品详情 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface IShGoodsAttrService extends IService<ShGoodsAttr> {

    List<ShGoodsAttr> selectAll(ShGoods shGoods);

    boolean deleteGoodsAttr(String id);

    boolean insertShGoodsAttr(ShGoodsAttr shGoodsAttr);

    boolean updateShGoodsAttr(ShGoodsAttr shGoodsAttr);

    boolean updateShGoodsAttr(List<ShGoodsAttr> list);

    boolean insertShGoodsAttr(List<ShGoodsAttr> list);

    boolean deleteGoodsAttr(List<String> list);
}
