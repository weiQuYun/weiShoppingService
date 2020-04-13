package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.HotelUserhotel;
import com.wqy.wx.back.plus3.entity.ShCoupon;
import com.wqy.wx.back.plus3.service.IShCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "优惠券表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/coupon")
public class ShCouponController {
    @Autowired
    private IShCouponService iShCouponService;

    @GetMapping("/list/{id}")
    @ApiOperation("获取所有优惠券")
    public List<ShCoupon> getAll(@PathVariable String id){
        return iShCouponService.selectByMemberId(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("使用优惠券")
    public String doShCoupon(@RequestBody HotelUserhotel hotelUserhotel,String id){
        iShCouponService.doShCoupon(hotelUserhotel,id);
        return "使用成功";
    }

    @GetMapping("/hotel")
    @ApiOperation("通过店铺id查询用于客户检查")
    public List<ShCoupon> getHotelShCoupon(@RequestBody HotelUserhotel hotelUserhotel){
        return iShCouponService.selectByHotelId(hotelUserhotel);
    }
}
