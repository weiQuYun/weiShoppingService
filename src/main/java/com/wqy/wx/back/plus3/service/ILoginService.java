package com.wqy.wx.back.plus3.service;

import com.wqy.wx.back.configer.Req;
import com.wqy.wx.back.dto.LoginDto;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: ILoginService
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/6 11:38
 * @Version V1.0
 */
public interface ILoginService {
    /**
     * 微信登陆接口
     *
     * @param phoneNumber
     * @param request
     * @param response
     * @return
     */
    Req vxLogin(String phoneNumber, HttpServletRequest request, HttpServletResponse response);

    /**
     * 后台登陆接口
     *
     * @param dto
     * @param request
     * @param response
     * @return
     */
    Req backLogin(@RequestBody LoginDto dto, HttpServletRequest request, HttpServletResponse response);

    /**
     * 登出接口
     *
     * @param userId
     * @param request
     * @param response
     * @return
     */
    Boolean logOut(String userId, HttpServletRequest request, HttpServletResponse response);
}
