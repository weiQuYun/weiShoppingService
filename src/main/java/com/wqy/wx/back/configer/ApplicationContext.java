package com.wqy.wx.back.configer;

import com.wqy.wx.back.configer.handler.ResultWarpReturnValueHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 替换默认的RequestResponseBodyMethodProcessor
 *
 * @Author licm
 * @Date 2019/4/8 23:46
 * @Description
 */
@Slf4j
@Configuration
@EnableCaching
public class ApplicationContext implements WebMvcConfigurer, InitializingBean {

    @Autowired(required = false)
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 获取SpringMvc的ReturnValueHandlers
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        // 新建一个List来保存替换后的Handler的List
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(returnValueHandlers);
        // 循环遍历找出RequestResponseBodyMethodProcessor
        for (HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                // 创建定制的Json格式处理Handler
                ResultWarpReturnValueHandler decorator = new ResultWarpReturnValueHandler(handler);
                // 使用定制的Json格式处理Handler替换原有的RequestResponseBodyMethodProcessor
                int index = handlers.indexOf(handler);
                handlers.set(index, decorator);
                break;
            }
        }
        // 重新设置SpringMVC的ReturnValueHandlers
        adapter.setReturnValueHandlers(handlers);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
