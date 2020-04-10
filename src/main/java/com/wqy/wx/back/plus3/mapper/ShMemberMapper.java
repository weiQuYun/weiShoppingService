package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShMember;
import org.apache.ibatis.annotations.Select;

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


    @Select("select * from sh_member where update_time>#{date}")
    List<ShMember> selectByTime(Date date);

    @Select("select * from sh_member where id = #{parentId}")
    ShMember selectByParentId(String parentId);
}
