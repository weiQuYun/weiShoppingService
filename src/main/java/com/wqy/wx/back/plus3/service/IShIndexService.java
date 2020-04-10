package com.wqy.wx.back.plus3.service;

import com.wqy.wx.back.dto.TemporaryMember;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.entity.ShIndex;

import java.util.List;

public interface IShIndexService {
    List<ShIndex> getNotice();

    boolean save(ShIndex shIndex);

    List<TemporaryMember> getTemporaryMember();

    List<ShGoods> getIndexShGoods();
}
