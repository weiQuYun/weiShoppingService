package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface ShCartMapper extends BaseMapper<ShCart> {

    @Delete("delete from sh_cart where goods_id = #{id}")
    void deleteByShGoodsId(String id);

    @Select("select * from sh_cart where user_id = #{id}")
    ShCart selectByMemberId(String id);
}
