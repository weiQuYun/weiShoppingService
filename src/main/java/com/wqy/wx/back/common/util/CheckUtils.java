package com.wqy.wx.back.common.util;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.configer.exception.BizException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: CheckUtils
 * @Description: TODO
 * @Author licm
 * @Date 2019/11/29 21:42
 * @Version V1.0
 * @Explain :
 **/
public class CheckUtils {

    /**
     * 判断数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (StringUtils.isNotBlank(str)) {
            Pattern pattern = Pattern.compile(Constant.REGEX_NUM);
            Matcher isNum = pattern.matcher(str);
            if (isNum.matches()) {
                return true;
            } else {
                throw new BizException("505", "不是数字");
            }
        } else {
            throw new BizException("505", "不能为空");
        }
    }

    /**
     * 判断数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber2(String str) {
        if (StringUtils.isNotBlank(str)) {
            Pattern pattern = Pattern.compile(Constant.REGEX_NUM);
            Matcher isNum = pattern.matcher(str);
            if (isNum.matches()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 判断主键
     *
     * @param str
     */
    public static void isKey(String str) {
        Pattern pattern = Pattern.compile(Constant.REGEX_KEY_ID);
        if (StringUtils.isNotBlank(str) && pattern.matcher(str).matches()) {

        } else {
            throw new BizException("505", "主键异常");
        }
    }

    public static void isListBlank(Collection collection, String msg) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(msg + "数据不能为空");
        }
    }

    public static void isStrBlank(String str, String msg) {
        if (StringUtils.isEmpty(str)) {
            throw new BizException(msg + "数据不能为空");
        }
    }

    public static void isStrBlank(String str, String msg, String reg) {
        if (StringUtils.isNotEmpty(str)) {
            Pattern pattern = Pattern.compile(reg);
            Matcher isNum = pattern.matcher(str);
            if (!isNum.matches()) {
                throw new BizException(msg + "格式异常");
            }
        } else {
            throw new BizException(msg + "数据不能为空");
        }
    }

    public static void isObjectBlank(Object o, String msg) {
        if (null == o) {
            throw new BizException(msg + "数据不能为空");
        }
    }
}
