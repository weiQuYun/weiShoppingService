package com.wqy.wx.back.configer.handler;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.wqy.wx.back.common.util.page.PageUtils;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一处理ResponseBody数据格式
 *
 * @Author: Autumn、
 * @Date: 2019/4/24 23:59
 * @Description:
 **/
//@Component
public class ResultWarpReturnValueHandler implements HandlerMethodReturnValueHandler {
    //    private static final String STATUS_CODE_SUCCEEDED = "200";
//    private static final String STATUS_CODE_INTERNAL_ERROR = "500";
    private final HandlerMethodReturnValueHandler delegate;

//    /** 委托 */
//    public ResultWarpReturnValueHandler() {
////        this.delegate = null;
//    }

    /**
     * 委托
     */
    public ResultWarpReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    /**
     * 判断返回类型是否需要转成字符串返回
     *
     * @param returnType 方法返回类型
     * @return 需要转换返回true，否则返回false
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
//        if (returnType.hasMethodAnnotation(ResponseBody.class)
//                || (!returnType.getDeclaringClass().equals(ModelAndView.class))
//                && returnType.getMethod().getDeclaringClass().isAnnotationPresent(RestController.class)) {
//            return true;
//        }
//
//        return false;
        return delegate.supportsReturnType(returnType);
    }

    /**
     * 返回值转换
     */
    @Override
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // 委托SpringMVC默认的RequestResponseBodyMethodProcessor进行序列化
        if (returnValue instanceof PageInfo) {
            PageInfo pageinfo = (PageInfo) returnValue;
            returnValue = PageUtils.toPageInfo(pageinfo);
        }
        if (returnValue instanceof Page) {
            Page pageinfo = (Page) returnValue;
            returnValue = PageUtils.toPage(pageinfo);
        }
        if (!(returnValue instanceof Result)) {
            Result result = new Result(returnValue);
            result.setCode("200");
            result.setMessage("success");
            HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
            result.setUrl(request.getServletPath());
            returnValue = result;
        }
        delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);

    }
}
