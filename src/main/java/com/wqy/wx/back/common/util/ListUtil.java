package com.wqy.wx.back.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import java.util.List;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: ListUtil
 * @Description: TODO
 * @Author licm
 * @Date 2019/12/10 16:23
 * @Version V1.0
 * @Explain : 集合操作工具类
 **/
public class ListUtil {
    /**
     * 将List元素拼装成逗号分隔的字符串
     */
    public static String list2String(List<String> arr) {
        if (CollectionUtils.isEmpty(arr)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : arr) {
            sb.append(obj).append(",");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 将List元素拼装成逗号分隔的字符串
     */
    public static String list2String(String[] arr) {
        if (ArrayUtils.isEmpty(arr)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : arr) {
            sb.append(obj).append(",");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
