package com.wqy.wx.back.plus2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TComment;

import java.util.List;

/**
 * <p>
 * 评论投诉表i 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface ITCommentService extends IService<TComment> {
    /**
     * 条件获取数据
     *
     * @param tComment
     * @return
     */
    List<TComment> getList(TComment tComment);

    /**
     * 条件获取分页数据
     *
     * @param tComment
     * @param pageDTO
     * @return
     */
    Page<TComment> getPage(TComment tComment, PageDTO pageDTO);
}
