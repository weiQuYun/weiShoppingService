package com.wqy.modules.shopping.service;

import com.baomidou.mybatisplus.service.IService;
import com.wqy.modules.shopping.entity.ShMember;
import com.wqy.modules.shopping.entity.ShMemberSub;

public interface IShMemberSubService extends IService<ShMemberSub> {


    /**
     * 获取某用户的上级
     * @param member
     * @return
     */
    public ShMemberSub getSupMember(ShMember member);



    /**
     * 获取某用户的上上级
     * @param member
     * @return
     */
    public ShMemberSub getUpUpMember(ShMember member);

}
