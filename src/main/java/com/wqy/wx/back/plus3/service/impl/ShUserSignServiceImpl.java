package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.CheckUtils;
import com.wqy.wx.back.common.util.DateUtil;
import com.wqy.wx.back.configer.exception.BizException;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShUserSign;
import com.wqy.wx.back.plus3.mapper.ShUserSignMapper;
import com.wqy.wx.back.plus3.service.IShUserSignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = BizException.class)
    @Override
    public Boolean signIn(String userId) {
        CheckUtils.isStrBlank(userId, "签到用户");
        ShMember shMember = new ShMember();
        shMember.setId(userId);
        shMember = shMember.selectById();
        if (shMember == null) {
            throw new BizException("签到账户不存在");
        }
        QueryWrapper<ShUserSign> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        query.like("sign_in", DateUtil.date2String(new Date()));
        List<ShUserSign> shUserSigns = this.list(query);
        if (CollectionUtils.isNotEmpty(shUserSigns)) {
            throw new BizException("您今天已经签到了");
        }
        ShUserSign shUserSign = new ShUserSign();
        shUserSign.setUserId(userId);
        //todo 积分+100
        if (shUserSign.insert()) {
            shMember.setIntegral(shMember.getIntegral() + 100L);
            shMember.updateById();
        }
        return shUserSign.insert();
    }

    @Override
    public List<ShUserSign> getList(String userId) {
        CheckUtils.isStrBlank(userId, "签到用户");
        QueryWrapper<ShUserSign> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        return this.list(query);
    }
}
