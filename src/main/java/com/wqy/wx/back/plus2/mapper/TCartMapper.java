package com.wqy.wx.back.plus2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus2.entity.TCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface TCartMapper extends BaseMapper<TCart> {

    @Delete("delete from t_cart where procute_id = #{id}")
    void deleteProduct(String id);

    @Select("select * from t_cart where menber_id = #{id}")
    List<TCart> selectBytMenberId(String id);
}
