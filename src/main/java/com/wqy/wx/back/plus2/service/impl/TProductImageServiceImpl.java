package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.plus2.entity.TProductImage;
import com.wqy.wx.back.plus2.mapper.TProductImageMapper;
import com.wqy.wx.back.plus2.service.ITProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品图片more 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
@Primary
@Service
public class TProductImageServiceImpl extends ServiceImpl<TProductImageMapper, TProductImage> implements ITProductImageService {

    @Autowired
    private TProductImageMapper tProductImageMapper;

    @Override
    public List<TProductImage> searchAll(String id) {
        return tProductImageMapper.searchByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteProductImage(String id) {
        //此处拿到的是实际ID
        tProductImageMapper.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Boolean insertProductImage(TProductImage tProductImage) {
        tProductImageMapper.insert(tProductImage);//图片应带有ID 故不在生成
        return true;
    }

    @Override
    @Transactional
    public Boolean updateProductImage(TProductImage tProductImage) {
        tProductImageMapper.deleteById(tProductImage.getId());
        tProductImageMapper.insert(tProductImage);
        return true;
    }

    @Override
    public void searchAll(int page, int size) {

    }

    @Override
    @Transactional
    public Boolean updateProductImage(List<TProductImage> list) {
        if (list.size() > 0) {
            for (TProductImage tProductImage : list) {
                tProductImageMapper.deleteById(tProductImage.getId());
                tProductImageMapper.insert(tProductImage);
            }
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean insertProductImage(List<TProductImage> list) {
        if (list.size() > 0) {
            for (TProductImage tProductImage : list) {
                tProductImageMapper.insert(tProductImage);
            }
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteProductImage(List<String> id) {
        if (id.size() > 0) {
            for (String pId : id) {
                tProductImageMapper.deleteById(pId);
            }
        }
        return true;
    }
}
