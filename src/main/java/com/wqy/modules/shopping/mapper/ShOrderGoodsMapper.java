package com.wqy.modules.shopping.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wqy.modules.shopping.entity.ShOrderGoods;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface ShOrderGoodsMapper extends BaseMapper<ShOrderGoods> {
    @Select("select * from sh_order_goods where id = #{id}")
    ShOrderGoods getOrderGoods(ShOrderGoods shOrderGoods);

}
