package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TComment;
import com.wqy.wx.back.plus2.mapper.TCommentMapper;
import com.wqy.wx.back.plus2.service.ITCommentService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论投诉表i 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
@Primary
@Service
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements ITCommentService {

    @Override
    public List<TComment> getList(TComment tComment) {
        QueryWrapper<TComment> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tComment, query);
        return this.list(query);
    }

    @Override
    public Page<TComment> getPage(TComment tComment, PageDTO pageDTO) {
        QueryWrapper<TComment> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tComment, query);
        Page<TComment> page = new Page<>(pageDTO.getPageIndex(), pageDTO.getPageSize());
        return this.page(page, query);
    }
}
