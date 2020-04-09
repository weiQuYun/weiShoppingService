package com.wqy.modules.shopping.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.shopping.entity.ShGoods;
import com.wqy.modules.shopping.entity.ShGoodsAttr;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface IShGoodsAttrService extends IService<ShGoodsAttr> {
    //添加
    void addGoodsAttr(ShGoodsAttr shGoodsAttr);

    //修改
    void updateGoodsAttr(ShGoodsAttr shGoodsAttr);

    //删除
    void deleteGoodsAttr(String id);

    //查询全部+分页+高级查询
    PageInfo<ShGoods> selectAll(Page page);

    //根据商品主键删除
    void deleteByGoods(String goodsId);

    //根据属性主键删除
    void deleteByAttr(String attrId);
}
