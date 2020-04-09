package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.CheckUtils;
import com.wqy.wx.back.common.util.IpUtils;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.dozer.IGenerator;
import com.wqy.wx.back.configer.Req;
import com.wqy.wx.back.configer.exception.BizException;
import com.wqy.wx.back.configer.redis.RedisUtil;
import com.wqy.wx.back.dto.LoginDto;
import com.wqy.wx.back.plus2.entity.TMenber;
import com.wqy.wx.back.plus2.entity.TUser;
import com.wqy.wx.back.plus2.service.ILoginService;
import com.wqy.wx.back.plus2.service.ITMenberService;
import com.wqy.wx.back.plus2.service.ITUserService;
import com.wqy.wx.back.plus2.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: LoginServiceImpl
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/6 11:39
 * @Version V1.0
 */
@Slf4j
@Primary
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private ITMenberService itMenberService;
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private ITUserService userService;
    @Autowired
    private IGenerator generator;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Req vxLogin(String phoneNumber, HttpServletRequest request, HttpServletResponse response) {
        CheckUtils.isStrBlank(phoneNumber, "手机号码", Constant.REGEX_MOBILE);
        TMenber tMenber = new TMenber();
        tMenber.setPhoneNumber(phoneNumber);
        List<TMenber> list = itMenberService.getList(tMenber);
        if (CollectionUtils.isNotEmpty(list) && list.size() == 1) {
            tMenber = list.get(0);
            Req vxLoginDto1 = loginReg(request, tMenber.getId());
            if (vxLoginDto1 != null) return vxLoginDto1;
            Req req = new Req();
            retrunData(phoneNumber, request, response, tMenber, req);
            return req;
        } else if (CollectionUtils.isEmpty(list)) {
            tMenber.setUserName("新用户");
            itMenberService.save(tMenber);
            Req req = new Req();
            retrunData(phoneNumber, request, response, tMenber, req);
            return req;
        } else if (list.size() > 1) {
            throw new BizException("W005", "存在相同的用户");
        }
        return null;
    }

    /**
     * 组装数据
     *
     * @param phoneNumber
     * @param request
     * @param response
     * @param tMenber
     * @param req
     */
    private void retrunData(String phoneNumber, HttpServletRequest request, HttpServletResponse response, TMenber tMenber, Req req) {
        req.setToken(tokenService.getToken(phoneNumber));
        req.setUserId(tMenber.getId());
        req.setUserName(tMenber.getUserName());
        req.setIp(request.getRemoteAddr());
        redisUtil.set(tMenber.getId(), req, Constant.LOGIN_TIME_OUT, TimeUnit.SECONDS);
        Cookie cookie = new Cookie("token", req.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public Req backLogin(LoginDto dto, HttpServletRequest request, HttpServletResponse response) {
        CheckUtils.isObjectBlank(dto, "登陆信息");
        CheckUtils.isStrBlank(dto.getUserName(), "用户名");
        CheckUtils.isStrBlank(dto.getPassword(), "密码");
        TUser tUser = generator.convert(dto, TUser.class);
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper = ParamUtils.reflect(tUser, queryWrapper);
        List<TUser> list = userService.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(list) && list.size() == 1) {
            tUser = list.get(0);
            Req vxLoginDto11 = loginReg(request, tUser.getId());
            if (vxLoginDto11 != null) return vxLoginDto11;
            Req req = new Req();
            returnData(request, response, tUser, req);
            return req;
        } else if (CollectionUtils.isEmpty(list)) {
            userService.insertTUser(tUser);
            Req req = new Req();
            returnData(request, response, tUser, req);
            return req;
        } else if (list.size() > 1) {
            throw new BizException("W005", "存在相同的用户");
        }
        return null;
    }

    /**
     * 组装数据
     *
     * @param request
     * @param response
     * @param tUser
     * @param req
     */
    private void returnData(HttpServletRequest request, HttpServletResponse response, TUser tUser, Req req) {
        req.setToken(tokenService.getToken(tUser.getUserName(), tUser.getPassword()));
        req.setUserId(tUser.getId());
        req.setUserName(tUser.getUserName());
        req.setIp(request.getRemoteAddr());
        req.setUserType(tUser.getRoleId());
        redisUtil.set(tUser.getId(), req, Constant.LOGIN_TIME_OUT, TimeUnit.SECONDS);
        Cookie cookie = new Cookie("token", req.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 登陆校验
     *
     * @param request
     * @param id
     * @return
     */
    private Req loginReg(HttpServletRequest request, String id) {
        Req vxLoginDto1 = (Req) redisUtil.get(id);
        if (vxLoginDto1 != null) {
            if (vxLoginDto1.getIp().equals(IpUtils.getIp(request))) {
                return vxLoginDto1;
            } else {
                log.info("登陆ip:{} 已有ip:{}", IpUtils.getIp(request), vxLoginDto1.getIp());
                throw new BizException("单点登陆，此用户已在其他设备上登陆");
            }
        }
        return null;
    }

    @Override
    public Boolean logOut(String userId, HttpServletRequest request, HttpServletResponse response) {
        CheckUtils.isStrBlank(userId, "用户");
        redisUtil.del(userId);
        return true;
    }
}
