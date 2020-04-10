package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.entity.ShGoodsAttr;
import com.wqy.wx.back.plus3.mapper.ShGoodsAttrMapper;
import com.wqy.wx.back.plus3.service.IShGoodsAttrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品详情表用于商品页面的商品详情 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShGoodsAttrServiceImpl extends ServiceImpl<ShGoodsAttrMapper, ShGoodsAttr> implements IShGoodsAttrService {

    @Autowired
    private ShGoodsAttrMapper shGoodsAttrMapper;
    @Override
    public List<ShGoodsAttr> selectAll(ShGoods shGoods) {
        return shGoodsAttrMapper.selectByGoodsId(shGoods.getId());
    }

    @Override
    public boolean deleteGoodsAttr(String id) {
        int i = shGoodsAttrMapper.deleteById(id);
        if (i==1)return true;
        return false;
    }

    @Override
    public boolean insertShGoodsAttr(ShGoodsAttr shGoodsAttr) {
        String charAndNumr = UUIDUtils.getCharAndNumr();
        shGoodsAttr.setId(charAndNumr);
        int insert = shGoodsAttrMapper.insert(shGoodsAttr);
        if (insert==1)return true;
        return false;
    }

    @Override
    public boolean updateShGoodsAttr(ShGoodsAttr shGoodsAttr) {
        int i = shGoodsAttrMapper.updateById(shGoodsAttr);
        if (i==1)return true;
        return false;
    }

    @Override
    public boolean updateShGoodsAttr(List<ShGoodsAttr> list) {
        if (list.size()>0) {
            for (ShGoodsAttr shGoodsAttr : list) {
                updateShGoodsAttr(shGoodsAttr);
            }
        }
        return true;
    }

    @Override
    public boolean insertShGoodsAttr(List<ShGoodsAttr> list) {
        if (list.size()>0) {
            for (ShGoodsAttr shGoodsAttr : list) {
                insertShGoodsAttr(shGoodsAttr);
            }
        }
        return true;
    }

    @Override
    public boolean deleteGoodsAttr(List<String> list) {
        if (list.size()>0) {
            for (String s : list) {
                deleteGoodsAttr(s);
            }
        }
        return true;
    }
}
