package com.wqy.wx.back.plus3.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wqy.wx.back.plus3.entity.HotelUserhotel;
import com.wqy.wx.back.plus3.service.IHotelUserhotelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import com.wqy.wx.back.common.Constant;

import java.util.List;

/**
* @author licm
* @since 2020-04-13
*/
@Api(tags = "线下店铺表接口管理")
@RestController
@RequestMapping(Constant.MAPPING+"/hotelUserhotel")
    public class HotelUserhotelController {

//    @Autowired
//    private IHotelUserhotelService iHotelUserhotelService;
//
//    @GetMapping("/list")
//    @ApiOperation("这里是获取所有的线下店铺地址的")
//    public List<HotelUserhotel> getAll(){
//        return iHotelUserhotelService.list();
//    }
//
//    @PostMapping("")
//    @ApiOperation("添加线下店铺")
//    public String addHotel(@RequestBody HotelUserhotel hotelUserhotel){
//        iHotelUserhotelService.save(hotelUserhotel);
//        return "添加成功";
//    }
//
//    @DeleteMapping("")
//    @ApiOperation("删除线下店铺")
//    public String deleteHotel(@RequestBody HotelUserhotel hotelUserhotel){
//        iHotelUserhotelService.remove(new QueryWrapper<>(hotelUserhotel));
//        return "删除成功";
//    }
//    @PostMapping("")
//    @ApiOperation("修改线下店铺")
//    public String updateHotel(@RequestBody HotelUserhotel hotelUserhotel){
//        iHotelUserhotelService.update(new QueryWrapper<>(hotelUserhotel));
//        return "删除成功";
//    }



}
