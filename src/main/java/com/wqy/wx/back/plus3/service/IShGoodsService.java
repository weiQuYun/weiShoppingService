package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus3.entity.ShGoods;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface IShGoodsService extends IService<ShGoods> {

    List<ShGoods> selectAll(ShGoods shGoods);

    Boolean deleteGoods(String id);

    Boolean insertShGoods(ShGoods shGoods);

    boolean updateShGoods(ShGoods shGoods);

    Page<ShGoods> getPageShGoods(ShGoods shGoods, PageDTO pageDTO);

    boolean updateShGoods(List<ShGoods> list);

    boolean insertShGoods(List<ShGoods> list);

    boolean deleteGoods(List<String> list);
}
