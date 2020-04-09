package com.wqy.wx.back.plus2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus2.entity.THotel;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 * 店铺表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface THotelMapper extends BaseMapper<THotel> {

    @Delete("delete from t_hotel where product_id = #{id}")
    void deleteProduct(String id);
}
