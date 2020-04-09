package com.wqy.wx.back.plus2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TOrderInfo;

import java.util.List;

/**
 * <p>
 * 成功订单页面 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface ITOrderInfoService extends IService<TOrderInfo> {
    /**
     * 条件获取数据
     *
     * @param tOrderInfo
     * @return
     */
    List<TOrderInfo> getList(TOrderInfo tOrderInfo);

    /**
     * 条件获取分页数据
     *
     * @param tOrderInfo
     * @param pageDTO
     * @return
     */
    Page<TOrderInfo> getPage(TOrderInfo tOrderInfo, PageDTO pageDTO);
}
