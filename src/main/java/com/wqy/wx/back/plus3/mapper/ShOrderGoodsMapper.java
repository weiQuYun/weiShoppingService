package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShOrderGoods;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 订单商品表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface ShOrderGoodsMapper extends BaseMapper<ShOrderGoods> {

    @Select("select * from sh_order_goods where order_id=#{orderId}")
    ShOrderGoods selectByOrderId(String orderId);
}
