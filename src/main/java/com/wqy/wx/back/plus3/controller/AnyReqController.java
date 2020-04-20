package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: AnyReqController
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/14 15:19
 * @Version V1.0
 */
@Api(tags = "微信扫码进入接口")
@RestController
@RequestMapping("/vx")
public class AnyReqController {

//    @GetMapping("/{id}")
//    @ApiOperation("微信扫码转发接口")
//    public void testForward(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Throwable{
//        System.out.println("转发"+id);
//        Cookie cookie = new Cookie("parent_id", id);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        request.getRequestDispatcher("/druid").forward(request, response);
//    }
    @GetMapping("/{id}")
    @ApiOperation("微信扫码转发接口")
    public void testRedirect( @PathVariable String id, HttpServletResponse response) throws Throwable{
        System.out.println("重定向");
        Cookie cookie = new Cookie("parent_id", id);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("https://www.baidu.com");
    }
}
