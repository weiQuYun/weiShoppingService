package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShVip;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * VIP表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface ShVipMapper extends BaseMapper<ShVip> {
    /**
     * 根据用户表vip_level查询vip_level的详情
     */
    @Select("select * from sh_vip where vip_level=#{vipLevel}")
    ShVip selectLevel(Integer vipLev);
}
