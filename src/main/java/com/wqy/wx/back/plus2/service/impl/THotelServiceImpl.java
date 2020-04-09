package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.THotel;
import com.wqy.wx.back.plus2.mapper.THotelMapper;
import com.wqy.wx.back.plus2.service.ITHotelService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 店铺表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
@Primary
@Service
public class THotelServiceImpl extends ServiceImpl<THotelMapper, THotel> implements ITHotelService {

    @Override
    public List<THotel> getList(THotel tHotel) {
        QueryWrapper<THotel> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tHotel, query);
        return this.list(query);
    }

    @Override
    public Page<THotel> getPage(THotel tHotel, PageDTO pageDTO) {
        QueryWrapper<THotel> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tHotel, query);
        Page<THotel> page = new Page<>(pageDTO.getPageIndex(), pageDTO.getPageSize());
        return this.page(page, query);
    }
}
