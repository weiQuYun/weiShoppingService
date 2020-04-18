package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import io.swagger.annotations.Api;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "字典表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/dict")
public class ShDictController {
 /*   @GetMapping
    public void test(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        System.out.println("===================");
       // String ip = request.getHeader("x-forwarded-for");
        Enumeration<String> headerNames = request.getHeaderNames();
        System.out.println(request.getHeader("host"));
        System.out.println(request.getHeader("accept-encoding"));
        System.out.println(request.getHeader("user-agent"));
        System.out.println(request.getHeader("accept"));
        System.out.println(request.getHeader("connection"));
        System.out.println("=============");
        for (int i = 0; i <100 ; i++) {
            System.out.println(headerNames.nextElement());
        }


        System.out.println(headerNames);
    }
*/
}
