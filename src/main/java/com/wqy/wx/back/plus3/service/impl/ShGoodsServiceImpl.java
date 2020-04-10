package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.mapper.ShAttributeMapper;
import com.wqy.wx.back.plus3.mapper.ShCartMapper;
import com.wqy.wx.back.plus3.mapper.ShGoodsAttrMapper;
import com.wqy.wx.back.plus3.mapper.ShGoodsMapper;
import com.wqy.wx.back.plus3.service.IShGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShGoodsServiceImpl extends ServiceImpl<ShGoodsMapper, ShGoods> implements IShGoodsService {

    @Autowired
    private ShGoodsMapper shGoodsMapper;
    @Autowired
    private ShGoodsAttrMapper shGoodsAttrMapper;
    @Autowired
    private ShCartMapper shCartMapper;
    @Autowired
    private ShAttributeMapper shAttributeMapper;

    @Override
    //暂时未有模糊查询之后需求更改
    public List<ShGoods> selectAll(ShGoods shGoods) {
        if (shGoods.getGoodsName() == null) {
            return shGoodsMapper.selectList(null);
        }
        QueryWrapper<ShGoods> queryMrapper = new QueryWrapper<ShGoods>();
        QueryWrapper<ShGoods> reflect = ParamUtils.reflect(shGoods, queryMrapper);
        return shGoodsMapper.selectList(reflect);
    }

    @Override
    @Transactional
    public Boolean deleteGoods(String id) {
        int i = shGoodsMapper.deleteById(id);
        //删除商品详情表
        shGoodsAttrMapper.deleteByShGoodsId(id);
        //删除购物车中该商品
        shCartMapper.deleteByShGoodsId(id);
        //这里需要知道这个删除是完全删除了这个商品
        if (i == 1) return true;
        return false;
    }

    //此处加入UUID
    @Override
    @Transactional
    public Boolean insertShGoods(ShGoods shGoods) {
        String charAndNumr = UUIDUtils.getCharAndNumr();
        shGoods.setId(charAndNumr);
        int insert = shGoodsMapper.insert(shGoods);
        if (insert == 1) return true;
        return false;
    }

    @Override
    @Transactional
    public boolean updateShGoods(ShGoods shGoods) {
        int i = shGoodsMapper.updateById(shGoods);
        if (i == 1) return true;
        return false;
    }

    @Override
    public Page<ShGoods> getPageShGoods(ShGoods shGoods, PageDTO pageDTO) {
        QueryWrapper<ShGoods> queryMrapper = new QueryWrapper<ShGoods>();
        QueryWrapper<ShGoods> reflect = ParamUtils.reflect(shGoods, queryMrapper);
        Page<ShGoods> PageShGoods = new Page<>(pageDTO.getPageIndex(), pageDTO.getPageSize());
        return shGoodsMapper.selectPage(PageShGoods, reflect);
    }

    @Override
    public boolean updateShGoods(List<ShGoods> list) {
        if (list.size() > 0) {
            for (ShGoods shGoods : list) {
                updateShGoods(shGoods);
            }
        }
        return true;
    }

    @Override
    public boolean insertShGoods(List<ShGoods> list) {
        if (list.size() > 0) {
            for (ShGoods shGoods : list) {
                insertShGoods(shGoods);
            }
        }
        return true;
    }

    @Override
    public boolean deleteGoods(List<String> list) {
        if (list.size() > 0) {
            for (String s : list) {
                deleteGoods(s);
            }
        }
        return true;
    }
}
