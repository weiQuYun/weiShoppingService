package com.wqy.wx.back.plus2.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.configer.Req;
import com.wqy.wx.back.dto.LoginDto;
import com.wqy.wx.back.plus2.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: LoginController
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/6 10:06
 * @Version V1.0
 */
@Api(tags = "登陆接口管理")
@RestController()
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/login/vx")
    @ApiOperation("微信登陆接口")
    public Req vxLogin(@ApiParam("手机号") @RequestBody String phoneNumber, HttpServletRequest request, HttpServletResponse response) {
        return loginService.vxLogin(phoneNumber, request, response);
    }

    @PostMapping("/login/back")
    @ApiOperation("后台登陆接口")
    public Req backLogin(@RequestBody LoginDto dto, HttpServletRequest request, HttpServletResponse response) {
        return loginService.backLogin(dto, request, response);
    }

    @PostMapping(Constant.MAPPING + "/logout")
    @ApiOperation("登出接口")
    public Boolean logOut(@RequestBody String userId, HttpServletRequest request, HttpServletResponse response) {
        return loginService.logOut(userId, request, response);
    }
}
