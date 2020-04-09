package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TCart;
import com.wqy.wx.back.plus2.entity.TMenber;
import com.wqy.wx.back.plus2.mapper.TCartMapper;
import com.wqy.wx.back.plus2.mapper.TProductMapper;
import com.wqy.wx.back.plus2.service.ITCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
@Primary
@Service
public class TCartServiceImpl extends ServiceImpl<TCartMapper, TCart> implements ITCartService {

    @Autowired
    private TCartMapper tCartMapper;
    @Autowired
    private TProductMapper tProductMapper;

    @Override
    public List<TCart> getList(TMenber tMenber) {
        //通过tMenberID 查询该用户所有的购物车
        List<TCart> tCartList = tCartMapper.selectBytMenberId(tMenber.getId());
        //暂定直接返回 有需要再更改
        return tCartList;
    }

    @Override
    public Page<TCart> getPage(TCart tCart, PageDTO pageDTO) {
        QueryWrapper<TCart> query = new QueryWrapper<>();
        query = ParamUtils.reflect(tCart, query);
        Page<TCart> page = new Page<>(pageDTO.getPageIndex(), pageDTO.getPageSize());
        return this.page(page, query);
    }

    @Override
    public boolean insertTCart(TCart tCart) {
        //新增tcart 此处应赋予UUID
        tCart.setId(UUIDUtils.getCharAndNumr());
        tCartMapper.insert(tCart);
        return true;
    }
}
