package com.wqy.wx.back.common.util;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.configer.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: ParamUtils
 * @Description: xx
 * @Author licm
 * @Date 2019/9/3 15:50
 * @Version V1.0
 * @Explain : 处理参数拼接
 **/
public class ParamUtils {

    private static Logger log = LoggerFactory.getLogger("ParamUtils");

    private final static String Y = "Y";

    private final static String TYPE_STRING = "string";

    private final static String TYPE_NUM = "numeric";

    /**
     * 循环拼接第一层数据
     * 如果有需要特殊处理的属性，传入前需要置空，后续自行拼接
     *
     * @throws Exception
     */
    public static <T> QueryWrapper<T> reflect(Object e, QueryWrapper<T> queryWrapper) {
        log.info("-------------------------★判断的属性 ×排除 √添加-----------------------------");
        try {
            Class cls = e.getClass();
            Field[] fields = cls.getDeclaredFields();
            StringBuffer buffer = new StringBuffer();
            for (Field f : fields) {
                f.setAccessible(true);
                String type = f.getGenericType().toString();
                buffer.append("\n★属性名:" + f.getName() + "\t 属性值:" + f.get(e) + "\t 类型:" + type);
                switchColumn(e, queryWrapper, f, type, buffer);
            }
            log.info(buffer.toString());
            log.info("--------------------------------------------------------------------------------");
            return queryWrapper;
        } catch (Exception e1) {
            throw new BizException("自动拼接参数失败");
        }
    }


    /**
     * 根据类型拼接参数
     *
     * @param e
     * @param queryWrapper
     * @param f
     * @param type
     * @throws IllegalAccessException
     */
    private static void switchColumn(Object e, QueryWrapper queryWrapper, Field f, String type, StringBuffer buffer) throws IllegalAccessException {
        if (Constant.DATE.equals(type) && f.get(e) != null) {
            buffer.append("\n√ Date 添加—> 属性名:" + f.getName() + "\t 属性值:" + f.get(e));
            queryWrapper.eq(CamelAndUnderLineConverter.humpToLine2(f.getName()), Date.valueOf(f.get(e).toString()));
        } else if (f.get(e) != null && !"null".equals(f.get(e).toString())) {
            buffer.append("\n√ " + type + " 添加—> 属性名:" + f.getName() + "\t 属性值:" + f.get(e));
            queryWrapper.eq(CamelAndUnderLineConverter.humpToLine2(f.getName()), f.get(e));
        }
    }


    /**
     * 获取属性值
     *
     * @param name 属性名
     * @param obj  类
     * @return 属性值
     */
    public static Object getFieldValue(String name, Object obj) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        Object value;
        try {
            Method m = obj.getClass().getMethod("get" + name);
            value = m.invoke(obj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            value = null;
        }
        return value;
    }

    /**
     * 处理数据库字段名称为实体类名称
     *
     * @param string
     * @return
     */
    public static String getStringValue(String string) {
        String zjVal = "";
        String[] strValz = string.split("_");
        for (int i = 0; i <= strValz.length - 1; i++) {
            zjVal += strValz[i].substring(0, 1).toUpperCase() + strValz[i].substring(1);
        }
        return zjVal;
    }
}
