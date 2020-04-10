package com.wqy.wx.back.plus3.service.impl;

import com.wqy.wx.back.plus3.entity.ShIndex;
import com.wqy.wx.back.plus3.mapper.ShIndexMapper;
import com.wqy.wx.back.plus3.service.IShIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
public class ShIndexServiceImpl  implements IShIndexService {
    @Autowired
    private ShIndexMapper shIndexMapper;
    @Override
    public ShIndex getNotice() {
        //直接获取所有首页表 拿去最后一个 因为首页公告表为Id自增表 直接获取所有公告之后拿去最后一个元素即可
        List<ShIndex> shIndex = shIndexMapper.selectList(null);
        return shIndex.get(shIndex.size()-1);//获取最后一个元素直接返回
    }

    @Override
    public boolean save(ShIndex shIndex) {
        shIndexMapper.insert(shIndex);
        return true;
    }
}
