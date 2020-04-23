package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface ShOrderMapper extends BaseMapper<ShOrder> {

    @Select("select * from sh_order where member_id = #{id}")
    ShOrder selectByOrderId(String id);

    @Select("select * from sh_order where member_id = #{id}")
    List<ShOrder> selectByMemberId(String id);

    @Select("select * from sh_order where send_status = #{integer}")
    List<ShOrder> selectBySend(Integer integer);

    @Select("select * from sh_order where pay_status = #{pay}")
    List<ShOrder> selectByPay(Integer pay);
}
