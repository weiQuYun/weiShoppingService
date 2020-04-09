package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TOrderInfo;
import com.wqy.wx.back.plus2.mapper.TOrderInfoMapper;
import com.wqy.wx.back.plus2.service.ITOrderInfoService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 成功订单页面 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
@Primary
@Service
public class TOrderInfoServiceImpl extends ServiceImpl<TOrderInfoMapper, TOrderInfo> implements ITOrderInfoService {

    @Override
    public List<TOrderInfo> getList(TOrderInfo tOrderInfo) {
        QueryWrapper<TOrderInfo> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tOrderInfo, query);
        return this.list(query);
    }

    @Override
    public Page<TOrderInfo> getPage(TOrderInfo tOrderInfo, PageDTO pageDTO) {
        QueryWrapper<TOrderInfo> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tOrderInfo, query);
        Page<TOrderInfo> page = new Page<>(pageDTO.getPageIndex(), pageDTO.getPageSize());
        return this.page(page, query);
    }
}
