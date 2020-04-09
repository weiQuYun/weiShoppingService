package com.wqy.wx.back.plus2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TCart;
import com.wqy.wx.back.plus2.entity.TOrder;

import java.util.List;

/**
 * <p>
 * 订单页面 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface ITOrderService extends IService<TOrder> {
    /**
     * 条件获取数据
     *
     * @param tOrder
     * @return
     */
    List<TOrder> getList(TOrder tOrder);

    /**
     * 条件获取分页数据
     *
     * @param tOrder
     * @param pageDTO
     * @return
     */
    Page<TOrder> getPage(TOrder tOrder, PageDTO pageDTO);

    TOrder insertOrder(List<TCart> tCartList) throws Exception;

    boolean updateByOrderId(TOrder tOrders) throws Exception;
}
