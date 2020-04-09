package com.wqy.wx.back.plus2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus2.entity.TProduct;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface TProductMapper extends BaseMapper<TProduct> {

    @Select("select * from t_product where cid = #{classid}")
    List<TProduct> searchProductByCatesId(String classid);
}
