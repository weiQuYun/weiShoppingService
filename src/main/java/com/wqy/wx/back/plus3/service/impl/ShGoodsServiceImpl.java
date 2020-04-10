package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.mapper.ShGoodsMapper;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import com.wqy.wx.back.plus3.service.IShGoodsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
    @Override
    public List<ShGoods> selectAll(ShGoods shGoods) {
        if (shGoods.getGoodsName()==null){
            return shGoodsMapper.selectList(null);
        }
        QueryWrapper<ShGoods> queryMrapper = new QueryWrapper<ShGoods>();
        QueryWrapper<ShGoods> reflect = ParamUtils.reflect(shGoods, queryMrapper);
        return shGoodsMapper.selectList(reflect);
    }

    @Override
    public Boolean deleteGoods(String id) {
        int i = shGoodsMapper.deleteById(id);
        if (i==1)return true;
        return false;
    }

    //此处加入UUID
    @Override
    public Boolean insertShGoods(ShGoods shGoods) {
        String charAndNumr = UUIDUtils.getCharAndNumr();
        shGoods.setId(charAndNumr);
        int insert = shGoodsMapper.insert(shGoods);
        if (insert==1)return true;
        return false;
    }
}
