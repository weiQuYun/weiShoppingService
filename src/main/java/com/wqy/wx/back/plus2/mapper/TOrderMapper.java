package com.wqy.wx.back.plus2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus2.entity.TOrder;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 订单页面 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface TOrderMapper extends BaseMapper<TOrder> {

    @Select("select * from t_order where order_number = #{orderNumber}")
    TOrder selectByOrderNumber(String orderNumber);
}
