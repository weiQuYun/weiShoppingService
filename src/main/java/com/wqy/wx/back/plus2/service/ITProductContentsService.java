package com.wqy.wx.back.plus2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus2.entity.TProductContents;

import java.util.List;

/**
 * <p>
 * 商品详情介绍表 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface ITProductContentsService extends IService<TProductContents> {


    TProductContents searchAll(String id);

    Boolean deleteProductContents(String id);

    Boolean insertProductContents(TProductContents tProductContents);

    Boolean updateProductContents(TProductContents tProductContents);

    void searchAll(int page, int size);

    void updateProductContents(List<TProductContents> list);

    void insertProductContents(List<TProductContents> list);

    void deleteProductContents(List<String> id);
}
