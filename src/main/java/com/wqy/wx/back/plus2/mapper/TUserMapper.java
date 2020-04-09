package com.wqy.wx.back.plus2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus2.entity.TUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 后台用户 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface TUserMapper extends BaseMapper<TUser> {

    @Select("select * from t_user where user_name = #{username}")
    List<TUser> selectByUserName(String username);
}
