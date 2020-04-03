package com.wqy.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.shopping.entity.ShGoods;
import com.wqy.modules.shopping.mapper.ShGoodsMapper;
import com.wqy.modules.shopping.service.IShGoodsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
@Service
public class ShGoodsServiceImpl implements IShGoodsService {

    @Autowired
    private ShGoodsMapper shGoodsMapper;
    @Override
    public Page<ShGoods> getGoodsPage(int page,int size) {
        PageHelper.startPage(page, size);
        List<ShGoods> shGoods = shGoodsMapper.selectPage(new RowBounds(page, size), null);
        System.out.println(shGoods.size());
        return new Page<>();
                //(Page<ShGoods>) shGoodsMapper.selectPage(new RowBounds(page,size),null);
    }

    @Override
    public List<ShGoods> searchAll() {
        return shGoodsMapper.selectList(null);
    }

    @Override
    public void addShGoods(ShGoods shGoods) {
        shGoodsMapper.insert(shGoods);
    }

    @Override
    public void updateShGoods(ShGoods shGoods) {
        shGoodsMapper.updateById(shGoods);
    }

    @Override
    public void deleteShGoods(String id) {
        ShGoods shGoods = new ShGoods();
        shGoods.setId(id);
        shGoodsMapper.deleteById(id);
    }
}
