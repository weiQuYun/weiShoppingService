package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.CheckUtils;
import com.wqy.wx.back.common.util.DateUtil;
import com.wqy.wx.back.configer.exception.BizException;
import com.wqy.wx.back.plus3.entity.ShUserSign;
import com.wqy.wx.back.plus3.mapper.ShUserSignMapper;
import com.wqy.wx.back.plus3.service.IShUserSignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 签到表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShUserSignServiceImpl extends ServiceImpl<ShUserSignMapper, ShUserSign> implements IShUserSignService {

    @Override
    public Boolean signIn(String userId) {
        CheckUtils.isStrBlank(userId, "签到用户");
        QueryWrapper<ShUserSign> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        query.like("sign_in", DateUtil.date2String(new Date()));
        List<ShUserSign> shUserSigns = this.list(query);
        if (CollectionUtils.isNotEmpty(shUserSigns)) {
            throw new BizException("您今天已经签到了");
        }
        ShUserSign shUserSign = new ShUserSign();
        shUserSign.setUserId(userId);
        //todo 积分+1
//        if(shUserSign.insert()){
//        }
        return shUserSign.insert();
    }
}
