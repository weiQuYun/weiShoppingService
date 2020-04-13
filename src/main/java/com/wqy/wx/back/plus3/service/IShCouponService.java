package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus3.entity.HotelUserhotel;
import com.wqy.wx.back.plus3.entity.ShCoupon;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 优惠券表 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface IShCouponService extends IService<ShCoupon> {


    List<ShCoupon> selectByMemberId(String id);

    void doShCoupon(HotelUserhotel hotelUserhotel, String id);

    List<ShCoupon> selectByHotelId(HotelUserhotel hotelUserhotel);
}
