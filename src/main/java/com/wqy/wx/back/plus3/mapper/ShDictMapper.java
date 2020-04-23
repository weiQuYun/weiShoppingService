package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShDict;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface ShDictMapper extends BaseMapper<ShDict> {

    @Select("select * from sh_dict where dict_type_code = #{sortId}")
    ShDict selectBySortId(String sortId);

    @Select("select * from sh_dict where dict_type_name = #{longId}")
    ShDict selectByLongId(String longId);
}
