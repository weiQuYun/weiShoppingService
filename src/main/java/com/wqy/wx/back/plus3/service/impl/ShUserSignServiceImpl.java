package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.CheckUtils;
import com.wqy.wx.back.common.util.DateUtil;
import com.wqy.wx.back.configer.exception.BizException;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShUser;
import com.wqy.wx.back.plus3.entity.ShUserSign;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import com.wqy.wx.back.plus3.mapper.ShUserMapper;
import com.wqy.wx.back.plus3.mapper.ShUserSignMapper;
import com.wqy.wx.back.plus3.service.IShMemberService;
import com.wqy.wx.back.plus3.service.IShUserSignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private IShMemberService memberService;
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
        return true;
    }

    @Override
    public List<ShUserSign> getList(String userId) {
        CheckUtils.isStrBlank(userId, "签到用户");
        QueryWrapper<ShUserSign> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        return this.list(query);
    }

    @Override
    public void deduction() {
        QueryWrapper<ShUserSign> query = new QueryWrapper<>();
        Date d = DateUtil.getLastPeroid(0,new Date());
        query.like("sign_in", DateUtil.date2String(d)+"%");
        List<ShUserSign> list = this.list(query);
        log.info("获取所有签到人");
        List<String> userIds = list.parallelStream().map(ShUserSign::getUserId).collect(Collectors.toList());
        QueryWrapper<ShMember> query1 = new QueryWrapper<>();
        query1.ne("id", userIds.toArray());
        log.info("获取没有签到人");
        List<ShMember> shUsers = memberService.list(query1);
        if(CollectionUtils.isNotEmpty(shUsers)){
            shUsers.forEach(item->{
                long fen = item.getIntegral();
                log.info("扣分");
                if(fen>=1000L){
                    item.setIntegral(fen-1000L);
                }else{
                    item.setIntegral(0L);
                }
            });
            memberService.updateBatchById(shUsers);
        }

    }
}
