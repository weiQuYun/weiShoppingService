package com.wqy.wx.back.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: MapUtil
 * @Description: TODO
 * @Author licm
 * @Date 2019/12/25 17:27
 * @Version V1.0
 * @Explain :  map 集合帮助类
 **/
public class MapUtil {
    /**
     * 添加参数
     *
     * @param map
     * @param key
     * @param value
     */
    public static void addMap(Map<String, Object> map, String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            map.put(key, value);
        }
    }
}
