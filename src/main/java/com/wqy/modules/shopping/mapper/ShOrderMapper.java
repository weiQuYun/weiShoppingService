package com.wqy.modules.shopping.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wqy.modules.shopping.entity.ShOrder;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface ShOrderMapper extends BaseMapper<ShOrder> {
    /**
     * 根据订单id查询
     * @param id
     * @return
     */
    ShOrder selectByOrderId(String id);

    /**
     * 修改
     * @param shOrder
     */
    void updateOrder(ShOrder shOrder);

}
