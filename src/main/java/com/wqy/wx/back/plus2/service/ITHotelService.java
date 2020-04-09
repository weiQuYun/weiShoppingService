package com.wqy.wx.back.plus2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.THotel;

import java.util.List;

/**
 * <p>
 * 店铺表 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface ITHotelService extends IService<THotel> {
    /**
     * 条件获取数据
     *
     * @param tHotel
     * @return
     */
    List<THotel> getList(THotel tHotel);

    /**
     * 条件获取分页数据
     *
     * @param tHotel
     * @param pageDTO
     * @return
     */
    Page<THotel> getPage(THotel tHotel, PageDTO pageDTO);
}
