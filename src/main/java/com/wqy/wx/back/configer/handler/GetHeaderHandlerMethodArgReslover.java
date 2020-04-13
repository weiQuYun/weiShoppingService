package com.wqy.wx.back.configer.handler;

import com.wqy.wx.back.configer.Req;
import com.wqy.wx.back.configer.annotation.GetHeader;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 成都微趣云网络科技有限公司
 *
 * @GETHeader 实现类
 * @ClassName: GetHeaderHandlerMethodArgReslover
 * @Description: TODO
 * @Author licm
 * @Date 2020/2/9 2:14
 * @Version V1.0
 **/
public class GetHeaderHandlerMethodArgReslover implements HandlerMethodArgumentResolver {

    /**
     * 判断是否支持使用@GETHeader注解的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //如果该参数注解有@CurrentUser且参数类型是User
        return methodParameter.getParameterAnnotation(GetHeader.class) != null && methodParameter.getParameterType() == Req.class;
    }

    /**
     * 注入参数值
     */
    @Override
    public Req resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //取得HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        //取出session中的User
        Req req = new Req();
        req.setToken(request.getHeader("token"));
//        req.setUserType(request.getHeader("userType"));
        req.setUserName(request.getHeader("userName"));
        req.setUserId(request.getHeader("userId"));
//        req.setShopId(request.getHeader("shopId"));
//        return StringUtil.stringToObject2(request.getHeader("req"),Req.class);
        return req;
    }
}
