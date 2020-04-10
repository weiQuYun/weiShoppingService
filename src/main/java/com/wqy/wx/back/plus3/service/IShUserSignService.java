package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus3.entity.ShUserSign;

/**
 * <p>
 * 签到表 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface IShUserSignService extends IService<ShUserSign> {
    /**
     * 签到
     * @param userId
     * @return
     */
    Boolean signIn(String userId);
}
