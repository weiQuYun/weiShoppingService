package com.wqy.wx.back.common;

import com.wqy.wx.back.common.util.DateUtil;

import java.util.Date;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: Constant
 * @Description: TODO
 * @Author licm
 * @Date 2019/11/29 21:41
 * @Version V1.0
 * @Explain :
 **/
public class Constant {
    /**
     * api
     */
    public final static String API = "req";
    /**
     * 请求前置
     */
    public final static String MAPPING = "/api/wqy";
    /**
     * decimal + integer (-MAX, MAX)
     */
    public final static String REGEX_REAL_NUMBER = "^[-\\+]?(\\d+|\\d+\\.\\d+)$";
    /**
     * 主键 校验
     */
    public final static String REGEX_KEY_ID = "^\\d{19}$";
    public final static String REGEX_NUM = "[0-9]*";

    /**
     * 默认时间
     */
    public static final String DEFAULT_DATE = "1900-01-01 00:00:00";

    /**
     * 字符数字
     */
    public static final Integer ZERO = 0;

    public static final Integer ONE = 1;

    public static final Integer TWO = 2;

    public static final Integer THREE = 3;

    public static final Integer FOUR = 4;

    public static final Integer FIVE = 5;

    public static final Integer SIX = 6;

    public static final Integer SEVEN = 7;

    public static final Integer EIGHT = 8;

    public static final Integer NINE = 9;

    /**
     * 数据类型  ParamUtils 中使用
     */
    public static final String INTEGER = "class java.lang.Integer";
    public static final String INT = "int";
    public static final String SHORT1 = "class java.lang.Short";
    public static final String SHORT2 = "short";
    public static final String DOUBLE1 = "class java.lang.Double";
    public static final String DOUBLE2 = "double";
    public static final String BOOLEAN1 = "class java.lang.Boolean";
    public static final String BOOLEAN2 = "boolean";
    public static final String DATE = "class java.util.Date";
    public static final String STRING = "class java.lang.String";
    public static final String BIG_DECIMAL = "class java.math.BIG_DECIMAL";
    public static final String LONG1 = "class java.lang.Long";
    public static final String LONG2 = "long";
    public static final String FLOAT = "float";

    public static final String defaultDate = DateUtil.dateToString(new Date());
    /**
     * 手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    /**
     * 登陆过期时间 秒
     */
    public static final long LOGIN_TIME_OUT = 60 * 60 * 2;
}
