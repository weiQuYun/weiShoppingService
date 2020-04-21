package com.wqy.wx.back.plus3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus3.entity.ShMoney;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 钱包 Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-11
 */
public interface ShMoneyMapper extends BaseMapper<ShMoney> {
    @Select("select * from sh_money where id=#{id}")
   ShMoney selecyByMemberId(String id);

}
