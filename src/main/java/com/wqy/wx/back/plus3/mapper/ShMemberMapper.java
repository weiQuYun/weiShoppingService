package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShMember;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface ShMemberMapper extends BaseMapper<ShMember> {
    /**
     * 新增用户
     *
     * @param shMember
     */
    @Insert("INSERT INTO sh_member( id,parent_id,username,password,email,phone,openid,integral,integral_change_count,integral_change_rate,ifs_captain,share_number,create_time)  VALUES   ( #{id},#{parentId},#{username},#{password},#{email},#{phone},#{openid},#{integral},#{integralChangeCount},#{integralChangeRate},#{ifsCaptain},#{shareNumber},#{createTime})")
    void addMember(ShMember shMember);

    /**
     * 开会员
     */
    @Update("update sh_member set lv_vip=#{lvVip} where id=#{id}")
    void addVipMember(Integer lvVip, String id);

    /**
     * 开会员后返点
     */
    @Update("update sh_member set integral=#{integral} where id=#{id}")
    void rebatesIntegral(Long integral, String id);

    /**
     * 推荐成功后增加上级990积分,可推荐次数减一
     */
    @Update("update sh_member set integral=integral+990 where id=#{id}")
    void updateIntegral(String id);

    /**
     * 根据id查询
     */
    @Select("select * from sh_member where id=#{id}")
    ShMember selectByid(String id);


    @Select("select * from sh_member where update_time>#{date}")
    List<ShMember> selectByTime(Date date);

    @Select("select * from sh_member where id = #{parentId}")
    ShMember selectByParentId(String parentId);

    /**
     * 查询全部会员
     * @return
     */
    @Select("select * from sh_member")
    List<ShMember> selectAll();
}
