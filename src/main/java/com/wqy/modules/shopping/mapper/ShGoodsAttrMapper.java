package com.wqy.modules.shopping.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wqy.modules.shopping.entity.ShGoodsAttr;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface ShGoodsAttrMapper extends BaseMapper<ShGoodsAttr> {
    //添加
    void addGoodsAttr(ShGoodsAttr shGoodsAttr);

    //修改
    void updateGoodsAttr(ShGoodsAttr shGoodsAttr);

    //删除
    void deleteGoodsAttr(String id);

    //查询全部+分页+高级查询
    List<ShGoodsAttr> selectAll();

    //根据商品主键删除
    void deleteByGoods(String goodsId);

    //根据属性主键删除
    void deleteByAttr(String attrId);

}
