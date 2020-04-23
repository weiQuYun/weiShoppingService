package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.dto.ShOrderDto;
import com.wqy.wx.back.plus3.entity.ShCart;
import com.wqy.wx.back.plus3.entity.ShOrder;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface IShOrderService extends IService<ShOrder> {

    ShOrder insertShOrder(ShCart shCart);

    Boolean updateShOrder(ShOrder shOrder);

    List<ShOrder> selectByShOrderId(String id);

    void updateShOrderSend(ShOrder shOrder);

    ShOrder selectByShOrderId(ShOrderDto shOrderDto);
}
