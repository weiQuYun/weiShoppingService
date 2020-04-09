package com.wqy.wx.back.common.util.dozer;

import java.util.List;
import java.util.Set;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: IGenerator
 * @Description: TODO
 * @Author licm
 * @Date 2019/11/29 21:14
 * @Version V1.0
 * @Explain :
 **/
public interface IGenerator {
    <T, S> T convert(S s, Class<T> clz);

    <T, S> List<T> convert(List<S> s, Class<T> clz);

    <T, S> Set<T> convert(Set<S> s, Class<T> clz);

    <T, S> T[] convert(S[] s, Class<T> clz);

    long convert(String str);

    long[] convert(String[] strings);
}
