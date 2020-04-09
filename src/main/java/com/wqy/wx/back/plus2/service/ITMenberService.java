package com.wqy.wx.back.plus2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TMenber;

import java.util.List;

/**
 * <p>
 * 会员表i 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface ITMenberService extends IService<TMenber> {
    /**
     * 条件获取数据
     *
     * @param tMenber
     * @return
     */
    List<TMenber> getList(TMenber tMenber);

    /**
     * 条件获取分页数据
     *
     * @param tMenber
     * @param pageDTO
     * @return
     */
    Page<TMenber> getPage(TMenber tMenber, PageDTO pageDTO);
}
