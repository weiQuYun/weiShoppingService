package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus2.entity.TProduct;
import com.wqy.wx.back.plus2.entity.TProductCates;
import com.wqy.wx.back.plus2.mapper.TProductCatesMapper;
import com.wqy.wx.back.plus2.mapper.TProductContentsMapper;
import com.wqy.wx.back.plus2.mapper.TProductImageMapper;
import com.wqy.wx.back.plus2.mapper.TProductMapper;
import com.wqy.wx.back.plus2.service.ITProductCatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
@Primary
@Service
public class TProductCatesServiceImpl extends ServiceImpl<TProductCatesMapper, TProductCates> implements ITProductCatesService {

    @Autowired
    private TProductCatesMapper tProductCatesMapper;
    @Autowired
    private TProductMapper tProductMapper;
    @Autowired
    private TProductContentsMapper tProductContentsMapper;
    @Autowired
    private TProductImageMapper tProductImageMapper;

    @Override
    public List<TProductCates> searchAll() {
        List<TProductCates> tProductCates = tProductCatesMapper.selectList(null);
        //查询所有商品封装
        for (TProductCates tProductCate : tProductCates) {
            String classId = tProductCate.getId();
            List<TProduct> list = tProductMapper.searchProductByCatesId(classId);
            for (TProduct tProduct : list) {
                //查询所有商品详情封装
                tProduct.setTProductContents(tProductContentsMapper.selectById(tProduct.getId()));
                //查询所有商品图片封装
                tProduct.setProduct_image(tProductImageMapper.searchByID(tProduct.getId()));
            }
            tProductCate.setTProductList(list);
        }
        return tProductCates;
    }

    @Override
    @Transactional
    public Boolean deleteProductCates(String id) {
        if (id.isEmpty()) {
            tProductCatesMapper.deleteById(id);
            return true;
        } else return true;
    }

    @Override
    @Transactional
    public Boolean insertProductCates(TProductCates tProductCates) {
        tProductCates.setId(UUIDUtils.getCharAndNumr());//此处生成UUID 用于productCates ID  productID他处生成
        tProductCatesMapper.insert(tProductCates);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateProductCates(TProductCates tProductCates) {
        tProductCatesMapper.updateById(tProductCates);
        return true;
    }

    @Override
    public Page<TProductCates> searchAll(int page, int size) {
        Page<TProductCates> tProductCates = tProductCatesMapper.selectPage(new Page<>(page, size), null);
        List<TProductCates> records = tProductCates.getRecords();//获取数据
        //查询所有商品封装
        for (TProductCates tProductCate : records) {
            String classId = tProductCate.getId();
            List<TProduct> list = tProductMapper.searchProductByCatesId(classId);
            for (TProduct tProduct : list) {
                //查询所有商品详情封装
                tProduct.setTProductContents(tProductContentsMapper.selectById(tProduct.getId()));
                //查询所有商品图片封装
                tProduct.setProduct_image(tProductImageMapper.searchByID(tProduct.getId()));
            }
            tProductCate.setTProductList(list);
        }
        //返回封装后数据
        return tProductCates;

    }

    @Override
    public void updateProductCates(List<TProductCates> list) {
        //不存在此方法
    }

    @Override
    public void insertProductCates(List<TProductCates> list) {
        //不存在此方法
    }

    @Override
    public void deleteProductCates(List<String> id) {
        //不存在此方法
    }
}
