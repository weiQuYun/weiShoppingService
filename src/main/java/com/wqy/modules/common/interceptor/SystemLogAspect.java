package com.wqy.modules.common.interceptor;

import com.wqy.config.Constants;
import com.wqy.modules.common.dto.output.ApiResult;
import com.wqy.utils.DateTimeUtils;
import com.wqy.utils.IpUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * <p> 系统日志处理 </p>
 *
 * @author : wqy
 * @description :
 * @date : 2019/9/18 15:25
 */
@Aspect
@Configuration
@Slf4j
public class SystemLogAspect {


    @Pointcut("execution(* com.wqy.modules.*.api.*Controller.*(..)))")
    public void systemLog() {
    }



}
