package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.service.IShMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "会员表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/shMember")
public class ShMemberController {
    @Autowired
    private IShMemberService iShMemberService;

}
