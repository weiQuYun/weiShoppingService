package com.wqy.modules.shopping.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wqy.modules.shopping.entity.ShCart;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface ShCartMapper extends BaseMapper<ShCart> {
    /**
     * 添加商品
     * @param shCart
     */
    void addCart(ShCart shCart);
    /**
     * 修改商品
     */
    void updateCart(ShCart shCart);
    /**
     * 查询购物车
     */
    List<ShCart> selectAll(String userId);

    /*
    *  删除商品
    * */
    void deleteCart(String goodsId,String userId);

}
