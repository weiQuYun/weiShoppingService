package com.wqy.wx.back.plus2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus2.entity.TProductImage;

import java.util.List;

/**
 * <p>
 * 商品图片more 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface ITProductImageService extends IService<TProductImage> {

    List<TProductImage> searchAll(String id);

    Boolean deleteProductImage(String id);

    Boolean insertProductImage(TProductImage tProductImage);

    Boolean updateProductImage(TProductImage tProductImage);

    void searchAll(int page, int size);

    Boolean updateProductImage(List<TProductImage> list);

    Boolean insertProductImage(List<TProductImage> list);

    Boolean deleteProductImage(List<String> id);
}
