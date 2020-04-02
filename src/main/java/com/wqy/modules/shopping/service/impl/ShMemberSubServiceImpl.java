package com.wqy.modules.shopping.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wqy.modules.shopping.entity.ShMember;
import com.wqy.modules.shopping.entity.ShMemberSub;
import com.wqy.modules.shopping.mapper.ShMemberSubMapper;
import com.wqy.modules.shopping.service.IShMemberSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("shMemberSubServiceImpl")
public class ShMemberSubServiceImpl extends ServiceImpl<ShMemberSubMapper,ShMemberSub> implements IShMemberSubService{


    /**
     * 获取某用户的上级
     * @param member
     * @return
     */
    @Override
    public ShMemberSub getSupMember(ShMember member) {

        return baseMapper.getSupMember(member);
    }

    //获取某用户的上上级
    @Override
    public ShMemberSub getUpUpMember(ShMember member) {
        return baseMapper.getUpUpMember(member);
    }
}
