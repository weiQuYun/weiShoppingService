package com.wqy.modules.shopping.service;

import com.baomidou.mybatisplus.service.IService;
import com.wqy.modules.shopping.entity.ShCart;
import com.wqy.modules.shopping.entity.ShOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface IShOrderService extends IService<ShOrder> {
    @Select("select * from where member_id=#{memberId}")
    ShOrder selectAll(List<ShCart> shCarts) throws Exception;

    boolean updateByOrderId(ShOrder shOrders) throws Exception;
}
