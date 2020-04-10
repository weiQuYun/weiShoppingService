package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus3.entity.ShIndex;

public interface IShIndexService  {
    ShIndex getNotice();

    boolean save(ShIndex shIndex);
}
