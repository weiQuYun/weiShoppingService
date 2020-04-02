package com.wqy.modules.shopping.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wqy.modules.shopping.entity.ShMember;
import com.wqy.modules.shopping.entity.ShMemberSub;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//用户下级表
public interface ShMemberSubMapper extends BaseMapper<ShMemberSub> {

    /**
     * 获取某用户的上级
     * @param member
     * @return
     */
    @Select("select * from sh_member_sub where user_sub=#{id}")
    @Results({
            @Result(property = "shUser", column = "user_id"),
            @Result(property = "shUserSub",column = "user_sub"),
            @Result(property = "is_agent",column = "isAgent")
    })
    public ShMemberSub getSupMember(ShMember member);


    /**
     * 获取某用户的上上级
     * @param member
     * @return
     */

    @Select("select * from sh_member_sub where user_sub IN  (SELECT user_id from sh_member_sub where user_sub= #{id} )")
    @Results({
            @Result(property = "shUser", column = "user_id"),
            @Result(property = "shUserSub",column = "user_sub"),
            @Result(property = "is_agent",column = "isAgent")
    })
    public ShMemberSub getUpUpMember(ShMember member);



}
