package com.wqy.modules.shopping.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wqy.modules.shopping.entity.ShOrderGoods;
import com.wqy.modules.shopping.mapper.ShOrderGoodsMapper;
import com.wqy.modules.shopping.mapper.ShOrderMapper;
import com.wqy.modules.shopping.service.IShOrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
@Service
public class ShOrderGoodsServiceImpl extends ServiceImpl<ShOrderGoodsMapper, ShOrderGoods> implements IShOrderGoodsService {

    @Autowired
    private ShOrderGoodsMapper shOrderGoodsMapper;
    @Override
    public ShOrderGoods getOrderGoods(ShOrderGoods shOrderGoods) {
        return shOrderGoodsMapper.getOrderGoods(shOrderGoods);
    }
}
