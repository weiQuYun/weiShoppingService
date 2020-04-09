package com.wqy.wx.back.plus2.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wqy.wx.back.plus2.service.ITokenService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: TokenServiceImpl
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/6 11:20
 * @Version V1.0
 */
@Primary
@Service
public class TokenServiceImpl implements ITokenService {

    @Override
    public String getToken(String user) {
        Date start = new Date();
        //一小时有效时间
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(user).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user));
        return token;
    }

    @Override
    public String getToken(String userName, String password) {
        Date start = new Date();
        //一小时有效时间
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(userName).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(password));
        return token;
    }
}