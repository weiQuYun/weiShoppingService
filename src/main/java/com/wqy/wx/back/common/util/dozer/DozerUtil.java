package com.wqy.wx.back.common.util.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: DozerUtil
 * @Description: TODO
 * @Author licm
 * @Date 2019/11/29 18:21
 * @Version V1.0
 * @Explain :
 **/
public class DozerUtil {
    private static Mapper mapper = new DozerBeanMapper();

    public static <T> List<T> transforList(List<?> sources, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (sources == null) {
            return list;
        }
        for (Object o : sources) {
            T t = transfor(o, clazz);
            list.add(t);
        }
        return list;
    }

    public static <T> Set<T> transforSet(Set<?> sources, Class<T> clazz) {
        Set<T> set = new HashSet<>();
        if (sources == null) {
            return set;
        }
        for (Object o : sources) {
            T t = transfor(o, clazz);
            set.add(t);
        }
        return set;
    }

    public static void transfor(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        mapper.map(source, target);
    }

    public static <T> T transfor(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, target);
    }
}
