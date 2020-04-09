package com.wqy.modules.shopping.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.shopping.entity.ShType;
import com.wqy.modules.shopping.mapper.ShTypeMapper;
import com.wqy.modules.shopping.service.IShTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
@Service
public class ShTypeServiceImpl extends ServiceImpl<ShTypeMapper, ShType> implements IShTypeService {

    @Autowired
    private ShTypeMapper shTypeMapper;


    @Override
    public PageInfo<ShType> selectAll(Page page,ShType shType) {
        int pageNumber=(int)page.getCurrentpage();
        int pageSize=(int)page.getSize();
        PageHelper.startPage(pageNumber, pageSize,true,true,true);
        List<ShType> shTypes1 = shTypeMapper.selectAll(shType);
        System.out.println(shTypes1);
        System.out.println(shTypes1);
        System.out.println("==========");
        PageInfo pageInfo = new PageInfo(shTypes1);
        System.out.println(pageInfo.toString());
        return pageInfo;
    }

    /*
*  添加
* */
    @Override
    @Transactional
    public void addType(ShType shType) {
        //生成UUID唯一标识符作为Id
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        //截取
        String substring = s.substring(0, 16);
        shType.setId(substring);
        //获取当前时间
        Date date = new Date();
        long time = date.getTime();
        int times=(int) time;
        shType.setCreateTime(times);
        shType.setUpdateTime(times);
        shTypeMapper.addType(shType);
    }
/*
*  跟新
* */
    @Override
    @Transactional
    public void updateType(ShType shType) {
        //设置更新时间
        Date date = new Date();
        long time = date.getTime();
        int times=(int) time;
        shType.setUpdateTime(times);
        shTypeMapper.updateType(shType);
    }
/*
*  删除
* */
    @Override
    @Transactional
    public void deleteType(String id) {
        shTypeMapper.deleteType(id);
    }

}
