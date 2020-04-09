package com.wqy.modules.shopping.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wqy.modules.shopping.entity.ShGoods;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface ShGoodsMapper extends BaseMapper<ShGoods> {
    //添加
    void addGoods(ShGoods shGoods);

    //搜索+分页+高级查询
    List<ShGoods> selectAll(ShGoods shGoods);

    //删除
    void deleteGoods(String id);

    //修改
    void updateGoods(ShGoods shGoods);

    //上架
    void putawayGoods(String id);

    //下架
    void soldOutGoods(String id);



}
