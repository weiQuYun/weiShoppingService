package com.wqy.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.shopping.entity.ShGoods;
import com.wqy.modules.shopping.entity.ShType;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface IShGoodsService  {
    /**
     * 查询全部商品类型
     * @return
     */
    PageInfo<ShGoods> selectAll(Page page, ShGoods shGoods);
    /**
     * 添加一个商品类型
     */
    void addGoods(ShGoods shGoods);
    /*
     *  修改
     * */
    void updateGoods(ShGoods shGoods);
    /**
     *  删除
     */
    void deleteGoods(String id);

    /**
     * 上架
     * @param id
     */
    void putawayGoods(String id);

    /**
     * 下架
     */
    void soldOutGoods(String id);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    ShGoods selectById(String id);

}
