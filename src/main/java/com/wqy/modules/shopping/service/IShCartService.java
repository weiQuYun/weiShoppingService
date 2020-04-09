package com.wqy.modules.shopping.service;

import com.baomidou.mybatisplus.service.IService;
import com.wqy.modules.shopping.entity.ShCart;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface IShCartService extends IService<ShCart> {
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
