package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus3.entity.ShIndex;
import com.wqy.wx.back.plus3.entity.TemporaryMember;

import java.util.List;

public interface IShIndexService  {
    List<ShIndex> getNotice();

    boolean save(ShIndex shIndex);

    List<TemporaryMember> getTemporaryMember();
}
