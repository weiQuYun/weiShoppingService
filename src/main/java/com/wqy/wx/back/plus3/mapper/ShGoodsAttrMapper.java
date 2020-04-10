package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShGoodsAttr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品详情表用于商品页面的商品详情 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface ShGoodsAttrMapper extends BaseMapper<ShGoodsAttr> {

    @Delete("delete from sh_goods_attr where goods_id = #{id}")
    void deleteByShGoodsId(String id);
    @Select("select * from sh_goods_attr where goods_id = #{id}")
    List<ShGoodsAttr> selectByGoodsId(String id);
}
