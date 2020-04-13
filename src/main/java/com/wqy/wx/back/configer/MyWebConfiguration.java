package com.wqy.wx.back.configer;

import com.wqy.wx.back.configer.handler.GetHeaderHandlerMethodArgReslover;
import com.wqy.wx.back.configer.handler.RequestHandler;
import com.wqy.wx.back.configer.interceptor.GlobalInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: MyWebConfiguration
 * @Description: TODO
 * @Author XXXX
 * @Date 2020/2/6 0:03
 * @Version V1.0
 **/
@Configuration
public class MyWebConfiguration extends WebMvcConfigurationSupport {
    @Resource
    private RequestHandler requestHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器
        registry.addInterceptor(requestHandler).addPathPatterns("/**");
        //注册拦截器
        registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/*/api/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //注册@CurrentUser注解的实现类
        argumentResolvers.add(new GetHeaderHandlerMethodArgReslover());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
