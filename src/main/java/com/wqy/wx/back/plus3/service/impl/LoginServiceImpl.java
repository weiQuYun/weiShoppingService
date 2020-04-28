package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.CheckUtils;
import com.wqy.wx.back.common.util.IpUtils;
import com.wqy.wx.back.common.util.OpenIdGetUtils;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.common.util.dozer.IGenerator;
import com.wqy.wx.back.configer.Req;
import com.wqy.wx.back.configer.exception.BizException;
import com.wqy.wx.back.configer.redis.RedisUtil;
import com.wqy.wx.back.dto.LoginDto;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShMoney;
import com.wqy.wx.back.plus3.entity.ShUser;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import com.wqy.wx.back.plus3.mapper.ShMoneyMapper;
import com.wqy.wx.back.plus3.service.ILoginService;
import com.wqy.wx.back.plus3.service.IShMemberService;
import com.wqy.wx.back.plus3.service.IShUserService;
import com.wqy.wx.back.plus3.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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
    private IShMemberService itMenberService;
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private IShUserService userService;
    @Autowired
    private IGenerator generator;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ShareIDChange shareIDChange;
    @Autowired
    private ShMoneyMapper shMoneyMapper;


    @Override
    public Req vxLogin(String phoneNumber, HttpServletRequest request, HttpServletResponse response) {
        CheckUtils.isStrBlank(phoneNumber, "云编码");
        String phoneNumber1 = getPhoneNumber(phoneNumber);//这里获取的code有问题 给他切了一下
        Map<String,String> map = OpenIdGetUtils.getOpenId(phoneNumber1);
        phoneNumber = map.get("openid");
        ShMember tMenber = new ShMember();
        tMenber.setOpenid(phoneNumber);
        QueryWrapper<ShMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", phoneNumber);
        List<ShMember> list = itMenberService.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(list) && list.size() == 1) {
            tMenber = list.get(0);
            Req vxLoginDto1 = loginReg(request, tMenber.getId());
            if (vxLoginDto1 != null) {
                return vxLoginDto1;
            }
            Req req = new Req();
            retrunData(phoneNumber, request, response, tMenber, req);
            return req;
        } else if (CollectionUtils.isEmpty(list)) {
            tMenber.setUsername("新用户" + System.currentTimeMillis());
            tMenber.setId(UUIDUtils.getCharAndNumr());
            tMenber.insert();
           //shareIDChange.insertParentLongSortId(tMenber.getId());
            //这里给用户新添加了钱包
            ShMoney shMoney1 = new ShMoney();
            shMoney1.setAmount(new BigDecimal(0));
            shMoney1.setId(tMenber.getId());
            shMoneyMapper.insert(shMoney1);

//            ShMoney shMoney = new ShMoney();
//            shMoney.setAmount(new BigDecimal(0));
//            shMoney.setId(tMenber.getId());
//            shMoney.insert();
            List<ShMember> list1 = itMenberService.list(queryWrapper);
            tMenber = list1.get(0);
            Req req = new Req();
            retrunData(phoneNumber, request, response, tMenber, req);
            return req;
        } else if (list.size() > 1) {
            throw new BizException("W005", "存在相同的用户");
        }
        return null;
    }

    private String getPhoneNumber(String phoneNumber) {
        String[] split = phoneNumber.split("\"");
        return split[3];
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
    private void retrunData(String phoneNumber, HttpServletRequest request, HttpServletResponse response, ShMember tMenber, Req req) {
        req.setToken(tokenService.getToken(phoneNumber));
        req.setUserId(tMenber.getId());
        req.setUserName(tMenber.getUsername());
        req.setUserType(tMenber.getLvVip().toString());
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
        ShUser tUser = generator.convert(dto, ShUser.class);
        QueryWrapper<ShUser> queryWrapper = new QueryWrapper<>();
        queryWrapper = ParamUtils.reflect(tUser, queryWrapper);
        List<ShUser> list = userService.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(list) && list.size() == 1) {
            tUser = list.get(0);
            Req vxLoginDto11 = loginReg(request, tUser.getId());
            if (vxLoginDto11 != null) {
                return vxLoginDto11;
            }
            Req req = new Req();
            returnData(request, response, tUser, req);
            return req;
        } else if (CollectionUtils.isEmpty(list)) {
            userService.save(tUser);
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
    private void returnData(HttpServletRequest request, HttpServletResponse response, ShUser tUser, Req req) {
        req.setToken(tokenService.getToken(tUser.getUsername(), tUser.getPassword()));
        req.setUserId(tUser.getId());
        req.setUserName(tUser.getUsername());
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
