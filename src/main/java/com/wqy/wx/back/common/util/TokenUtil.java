package com.wqy.wx.back.common.util;

import com.auth0.jwt.JWT;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: TokenUtil
 * @Description: TODO
 * @Author XXXX
 * @Date 2020/2/5 22:52
 * @Version V1.0
 **/
public class TokenUtil {
    public static String getTokenUserId() {
        String token = getRequest().getHeader("token");// 从 http 请求头中取出 token
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    public static String getReq() {
        String req = getRequest().getHeader("req");// 从 http 请求头中取出 token
        String userId = JWT.decode(req).getAudience().get(0);
        return userId;
    }

    /**
     * 获取userId
     *
     * @return
     */
    public static Long getUserId() {
        String userId = getRequest().getHeader("userId");
        if (StringUtils.isBlank(userId) || !CheckUtils.isNumber2(userId)) {
            return null;
        }
        return Long.valueOf(userId);
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}
