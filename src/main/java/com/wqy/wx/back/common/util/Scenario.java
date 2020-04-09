package com.wqy.wx.back.common.util;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: Scenario
 * @Description: 用于标识测试的场景 标识
 * @Author xiongshibo
 * @Date 2019/10/9 17:50
 * @Version V1.0
 **/

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Scenario {
    String value() default "";
}
