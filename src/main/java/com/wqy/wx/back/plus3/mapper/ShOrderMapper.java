package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShOrder;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface ShOrderMapper extends BaseMapper<ShOrder> {

    @Select("select * from sh_order where order_id = #{id}")
    ShOrder selectByOrderId(String id);
}
