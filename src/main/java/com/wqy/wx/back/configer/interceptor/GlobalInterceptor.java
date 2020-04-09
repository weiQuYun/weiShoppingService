package com.wqy.wx.back.configer.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: GlobalInterceptor
 * @Description: TODO
 * @Author XXXX
 * @Date 2020/2/9 1:40
 * @Version V1.0
 **/
public class GlobalInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token=request.getHeader("req");
//        //判断路径需要拦截
//        //....code
//
//        //如果token有效
//        if(!TokenUtil.isExpire(token)){
//            User user = TokenUtil.getUser(token);
//            //我们将解析的用户结果先放入session中
//            request.getSession().setAttribut("currentUser",user);
//        }
        return true;
    }
}
