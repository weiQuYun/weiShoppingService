package com.wqy.wx.back.plus2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus2.entity.TOrderInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 成功订单页面 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface TOrderInfoMapper extends BaseMapper<TOrderInfo> {

    @Select("select * from t_order_info where order_id = #{orderNumber}")
    List<TOrderInfo> searchByOrderNumber(String orderNumber);
}
