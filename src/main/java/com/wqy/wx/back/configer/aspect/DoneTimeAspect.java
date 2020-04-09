package com.wqy.wx.back.configer.aspect;


import com.alibaba.fastjson.JSON;
import com.wqy.wx.back.configer.annotation.DoneTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class DoneTimeAspect {
    /**
     * @Fields : 字段最长输出设置,为了避免字符太长而刷屏
     */
    public final Integer subLogLength = 3000;

    @Around("@annotation(doneTime)")
    public Object around(ProceedingJoinPoint joinPoint, DoneTime doneTime) throws Throwable {
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始
        //得到返回結果
        Object result = joinPoint.proceed();
        clock.stop();  //计时结束
        //太长的返回结果 予以过滤后返回
        String resultJson = JSON.toJSONString(result);
        //方法参数类型，转换成简单类型
        LoggerFactory.getLogger(joinPoint.getTarget().getClass()).info(
                "耗时" + clock.getTotalTimeSeconds() + "秒|" + joinPoint.getSignature().getName() +
                        "|参数:" + JSON.toJSONString(joinPoint.getArgs()) +
                        " 结果:" + (StringUtils.isNotEmpty(resultJson) && resultJson.length() > subLogLength ?
                        resultJson.substring(0, subLogLength) + "......(已省略" + (resultJson.length() - subLogLength) + "个字符)"
                        : resultJson)
        );
        return result;
    }
}

