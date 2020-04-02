package com.wqy.modules.shopping.service;

import com.baomidou.mybatisplus.service.IService;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.common.pojo.PageResult;
import com.wqy.modules.shopping.entity.ShGoods;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface IShGoodsService  {
    Page<ShGoods> getGoodsPage(int page,int size);

    List<ShGoods> searchAll();

    void addShGoods(ShGoods shGoods);

    void updateShGoods(ShGoods shGoods);

    void deleteShGoods(String id);
}
