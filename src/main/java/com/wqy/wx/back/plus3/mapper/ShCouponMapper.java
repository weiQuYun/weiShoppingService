package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShCoupon;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 优惠券表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface ShCouponMapper extends BaseMapper<ShCoupon> {

    @Select("select * from sh_coupon where member_id = #{id}")
    List<ShCoupon> selectByMemberId(String id);

    @Select("select * from sh_coupon where hotel_id = ${id}")
    List<ShCoupon> selectByHotelId(String id);
}
