package com.wqy.modules.shopping.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wqy.modules.shopping.entity.ShType;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface ShTypeMapper extends BaseMapper<ShType> {
    //查询全部
    List<ShType> selectAll(ShType shType);
    //添加
    void addType(ShType shType);
    //修改
    void updateType(ShType shType);
    //删除
    void deleteType(String id);
}
