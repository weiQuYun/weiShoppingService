package com.wqy.wx.back.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.wqy.wx.back.configer.exception.BizException;

public class StringUtil {
    /**
     * 解析占位符
     *
     * @param text
     * @param objects
     * @return
     */
    public static String format(String text, Object[] objects) {
        String[] texts = text.split("%s");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < texts.length; i++) {
            if (objects.length > i) {
                buffer.append(texts[i] + objects[i]);
            } else {
                buffer.append(texts[i]);
            }
        }
        return buffer.toString();
    }

    /**
     * java 对象 -> json 字符串
     *
     * @param object
     * @return
     * @throws Throwable
     */
    public static String toString(Object object) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    /**
     * json 字符串-> java 对象
     *
     * @param
     * @return
     */
    public static <T> T stringToObject(String value, Class<T> t) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(value, new TypeReference<T>() {
            });
        } catch (Exception e) {
            throw new BizException("数据转换异常");
        }

    }

    /**
     * json 字符串-> java 对象
     *
     * @param
     * @return
     */
    public static <T> T stringToObject2(String value, Class<T> t) {
        return new Gson().fromJson(value, t);
    }
}
