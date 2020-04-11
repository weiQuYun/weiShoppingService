package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShVip;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import com.wqy.wx.back.plus3.mapper.ShVipMapper;
import com.wqy.wx.back.plus3.service.IShMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShMemberServiceImpl extends ServiceImpl<ShMemberMapper, ShMember> implements IShMemberService {
    @Autowired
    private ShMemberMapper shMemberMapper;
    @Autowired
    private ShVipMapper shVipMapper;



    @Override
    @Transactional
    public Boolean addMember(ShMember shMember) {
        if (!StringUtils.isEmpty(shMember.getParentId())) { //先判断他有没有上级，如果有进判断，如果没有直接添加设置为团长
            ShMember shMember1 = shMemberMapper.selectByParentId(shMember.getParentId());
            if (shMember1.getShareNumber() == null || shMember1.getShareNumber() == 0) { //判断是否有推荐次数，没有就返回false，直接不添加
                return false;
            } else { //如果有推荐次数，就添加
                String uuid = UUIDUtils.getCharAndNumr();
                shMember.setId(uuid);
                Date date = new Date();
                shMember.setCreateTime(date);
                String parentId = shMember.getParentId();
                shMemberMapper.addMember(shMember);
                shMemberMapper.updateIntegral(parentId);
                return true;

            }
        } else {//直接设置为团长
            String uuid = UUIDUtils.getCharAndNumr();
            shMember.setId(uuid);
            Date date = new Date();
            shMember.setCreateTime(date);
            //设置是团长
            shMember.setIfsCaptain(1);
            String parentId = shMember.getParentId();
            shMemberMapper.addMember(shMember);
            shMemberMapper.updateIntegral(parentId);
            return true;
        }
    }

    /**
     * 新建会员
     *
     * @param lvVip
     * @param id
     */
    @Override
    @Transactional
    public void addVipMember(Integer lvVip, String id) {
        shMemberMapper.addVipMember(lvVip, id);
        ShMember shMember1 = shMemberMapper.selectByid(id);
        //查找该vip等级的金额好返点
        //===================
        ShVip shVip = shVipMapper.selectLevel(lvVip);
        Integer vipPrice = shVip.getVipPrice();
        if (vipPrice == null || vipPrice == 0) {
            vipPrice = 0;

        }
        float num = (float) Constant.REBATES / 100;
        int price = vipPrice.intValue();
        float v = price * num;
        int i = (int) v;
        Integer integral = new Integer(i);
        // System.out.println(integral);
        //查询他的上级 一层
        ShMember shMember = shMemberMapper.selectByParentId(shMember1.getParentId());
        Long integral1 = shMember.getIntegral();
        //System.out.println("=============");
        //System.out.println(integral);
        Long integrals = integral + integral1;
        System.out.println(integral);
        String id1 = shMember.getId();
        System.out.println(id1);
        System.out.println(shMember.getIntegral());
        this.rebatesIntegral(integrals, id1);

    }

    @Override
    public ShMember selectById(String id) {
        return shMemberMapper.selectByid(id);
    }

    /**
     * 积分返点
     *
     * @param integral
     * @param id
     */
    @Override
    @Transactional
    public void rebatesIntegral(Long integral, String id) {
        shMemberMapper.rebatesIntegral(integral, id);
    }


}
