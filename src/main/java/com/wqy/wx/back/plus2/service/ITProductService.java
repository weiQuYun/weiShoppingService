package com.wqy.wx.back.plus2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus2.entity.TProduct;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface ITProductService extends IService<TProduct> {

    List<TProduct> searchAll(TProduct tProduct);

    void deleteProduct(String id);

    void deleteProduct(List<String> list);

    void insertProduct(TProduct tProduct);

    void insertProduct(List<TProduct> list);

    void updateProduct(TProduct tProduct);

    void updateProduct(List<TProduct> list);

    Page<TProduct> searchAll(int page, int size, TProduct tProduct);

}
