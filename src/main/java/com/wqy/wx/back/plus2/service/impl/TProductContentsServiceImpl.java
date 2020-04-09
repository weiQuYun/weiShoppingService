package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.plus2.entity.TProductContents;
import com.wqy.wx.back.plus2.mapper.TProductContentsMapper;
import com.wqy.wx.back.plus2.service.ITProductContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品详情介绍表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
@Primary
@Service
public class TProductContentsServiceImpl extends ServiceImpl<TProductContentsMapper, TProductContents> implements ITProductContentsService {

    @Autowired
    private TProductContentsMapper tProductContentsMapper;

    @Override
    public TProductContents searchAll(String id) {
        return tProductContentsMapper.selectById(id);
    }

    @Override
    @Transactional
    public Boolean deleteProductContents(String id) {
        tProductContentsMapper.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Boolean insertProductContents(TProductContents tProductContents) {
        //删除原来的描述
        deleteProductContents(tProductContents.getId());//此处不在生成UUID 传递过来的值中已经有了用原来的
        tProductContentsMapper.insert(tProductContents);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateProductContents(TProductContents tProductContents) {
        //删除原来的描述
        deleteProductContents(tProductContents.getId());
        tProductContentsMapper.update(tProductContents, null);
        return true;
    }

    @Override
    public void searchAll(int page, int size) {

    }

    @Override
    public void updateProductContents(List<TProductContents> list) {

    }

    @Override
    public void insertProductContents(List<TProductContents> list) {

    }

    @Override
    public void deleteProductContents(List<String> id) {

    }
}
