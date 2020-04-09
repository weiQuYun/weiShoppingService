package com.wqy.wx.back.plus2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TCart;
import com.wqy.wx.back.plus2.entity.TMenber;

import java.util.List;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface ITCartService extends IService<TCart> {
    /**
     * 条件获取数据
     *
     * @param
     * @return
     */
    List<TCart> getList(TMenber tMenber);

    /**
     * 条件获取分页数据
     *
     * @param tCart
     * @param pageDTO
     * @return
     */
    Page<TCart> getPage(TCart tCart, PageDTO pageDTO);

    boolean insertTCart(TCart tCart);
}
