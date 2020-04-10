package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus3.entity.ShType;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface IShTypeService extends IService<ShType> {

    List<ShType> selectAll();

    ShType selectIndex();

    Boolean updateShType(ShType shType);

    Boolean deleteById(String id);

    Boolean insertShType(ShType shType);

    boolean updateShType(List<ShType> list);

    boolean insertShType(List<ShType> list);

    boolean deleteShType(List<String> list);
}
