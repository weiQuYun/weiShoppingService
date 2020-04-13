package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.plus3.entity.HotelUserhotel;
import com.wqy.wx.back.plus3.entity.ShCoupon;
import com.wqy.wx.back.plus3.mapper.ShCouponMapper;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import com.wqy.wx.back.plus3.service.IShCouponService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShCouponServiceImpl extends ServiceImpl<ShCouponMapper, ShCoupon> implements IShCouponService {

    @Autowired
    private ShCouponMapper shCouponMapper;
    @Autowired
    private ShMemberMapper shMemberMapper;
    @Override
    public List<ShCoupon> selectByMemberId(String id) {
        return shCouponMapper.selectByMemberId(id);
    }

    @Override
    public void doShCoupon(HotelUserhotel hotelUserhotel,String id) {
        ShCoupon shCoupon = shCouponMapper.selectById(id);
        shCoupon.setHotelId(hotelUserhotel.getHotelId());
        shCoupon.setStuts(true);
        shCouponMapper.updateById(shCoupon);
        //此时就做了修改
    }

    @Override
    public List<ShCoupon> selectByHotelId(HotelUserhotel hotelUserhotel) {
        List<ShCoupon> shCoupons = shCouponMapper.selectByHotelId(hotelUserhotel.getHotelId());
        for (ShCoupon shCoupon : shCoupons) {
            shCoupon.setShMember(shMemberMapper.selectById(shCoupon.getMemberId()));
        }
        return shCoupons;
    }
}
