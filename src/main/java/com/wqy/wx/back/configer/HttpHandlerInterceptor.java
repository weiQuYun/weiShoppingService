package com.wqy.wx.back.configer;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * 请求拦截器： 返回结果 所有reponse输出
 */
public class HttpHandlerInterceptor implements WebRequestInterceptor {


    @Override
    public void preHandle(WebRequest webRequest) throws Exception {
        System.out.println("收到请求");
    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {
//        https://zhuanlan.zhihu.com/p/33667406
    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {
        System.out.println("释放资源");
    }
}
