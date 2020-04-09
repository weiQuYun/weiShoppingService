package com.wqy.wx.back.common.util.dozer;


import com.wqy.wx.back.common.util.CheckUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: BeanCopierGeneratorImpl
 * @Description: TODO
 * @Author licm
 * @Date 2019/11/29 21:14
 * @Version V1.0
 * @Explain :
 **/
@Primary
@Component
public class BeanCopierGeneratorImpl implements IGenerator {

    public BeanCopierGeneratorImpl() {
    }

    @Override
    public <T, S> T convert(S s, Class<T> clz) {
        return DozerUtil.transfor(s, clz);
    }

    @Override
    public <T, S> List<T> convert(List<S> s, Class<T> clz) {
        return DozerUtil.transforList(s, clz);
    }

    @Override
    public <T, S> Set<T> convert(Set<S> s, Class<T> clz) {
        return new HashSet(DozerUtil.transforSet(s, clz));
    }

    @Override
    public <T, S> T[] convert(S[] s, Class<T> clz) {
        if (s == null) {
            return null;
        } else {
            T[] arr = (T[]) ((T[]) Array.newInstance(clz, s.length));

            for (int i = 0; i < s.length; ++i) {
                arr[i] = DozerUtil.transfor(s[i], clz);
            }
            return arr;
        }
    }

    @Override
    public long convert(String str) {
        if (CheckUtils.isNumber(str)) {
            return Long.valueOf(str);
        } else {
            return -1L;
        }
    }

    @Override
    public long[] convert(String[] strings) {
        return Arrays.stream(strings).mapToLong((s) -> {
            return convert(s);
        }).toArray();
    }
}
