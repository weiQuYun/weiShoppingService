package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TMenber;
import com.wqy.wx.back.plus2.mapper.TMenberMapper;
import com.wqy.wx.back.plus2.service.ITMenberService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员表i 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
@Primary
@Service
public class TMenberServiceImpl extends ServiceImpl<TMenberMapper, TMenber> implements ITMenberService {

    @Override
    public List<TMenber> getList(TMenber tMenber) {
        QueryWrapper<TMenber> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tMenber, query);
        return this.list(query);
    }

    @Override
    public Page<TMenber> getPage(TMenber tMenber, PageDTO pageDTO) {
        QueryWrapper<TMenber> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tMenber, query);
        Page<TMenber> page = new Page<>(pageDTO.getPageIndex(), pageDTO.getPageSize());
        return this.page(page, query);
    }
}
