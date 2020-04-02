package com.wqy.modules.shopping.mapper;

import com.wqy.modules.shopping.entity.ShGoods;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface ShGoodsMapper extends Mapper<ShGoods> {
    @Override
    @Select("selct * from sh_goods")
    List<ShGoods> selectAll();

}
