package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShGoods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface ShGoodsMapper extends BaseMapper<ShGoods> {

    @Select("select * from sh_goods where type_id = #{id}")
    List<ShGoods> selectByIndex(String id);
}
