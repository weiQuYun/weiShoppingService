package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus3.entity.HotelUserhotel;
import com.wqy.wx.back.plus3.entity.ShUser;
import com.wqy.wx.back.plus3.mapper.ShUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "用户表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/user")
public class ShUserController {
    @Autowired
    private ShUserMapper shUserMapper;
    @PostMapping("")
    @ApiOperation("添加用户")
    public String addHotel(@RequestBody ShUser shUser){
        shUser.setId(UUIDUtils.getCharAndNumr());
        shUserMapper.insert(shUser);
        return "添加成功";
    }

}
