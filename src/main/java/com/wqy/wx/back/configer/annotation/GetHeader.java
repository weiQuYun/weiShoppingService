package com.wqy.wx.back.configer.annotation;

import java.lang.annotation.*;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: GetHeader
 * @Description: TODO
 * @Author XXXX
 * @Date 2020/2/9 2:11
 * @Version V1.0
 **/
@Target({ElementType.PARAMETER})//Annotation所修饰的对象范围:方法参数
@Retention(RetentionPolicy.RUNTIME)//Annotation被保留时间:运行时保留(有效)
@Documented//标记注解:java工具文档化
public @interface GetHeader {
}
