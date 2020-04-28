package com.wqy.wx.back.plus3.service.impl;

import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus3.entity.ShDict;
import com.wqy.wx.back.plus3.mapper.ShDictMapper;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareIDChange {
    @Autowired
    private ShMemberMapper shMemberMapper;
    @Autowired
    private ShDictMapper shDictMapper;
    public String getParentLong(String sortId){
        ShDict shDict = shDictMapper.selectBySortId(sortId);
        return shDict.getDictTypeName();
    }
    public String getParentsort(String longId){
        ShDict shDict = shDictMapper.selectByLongId(longId);
        return shDict.getDictTypeCode();
    }
    public Boolean insertParentLongSortId(String memberId){
        ShDict shDict = new ShDict();
        shDict.setDictTypeName(memberId);
        shDict.setDictTypeCode(UUIDUtils.getParent());
//        shDictMapper.insert(shDict);
        return true;
    }

}
