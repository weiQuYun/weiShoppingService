package com.wqy.modules.shopping.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.shopping.entity.ShType;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface IShTypeService extends IService<ShType> {
    /**
     * 查询全部商品类型
     * @return
     */
    PageInfo<ShType> selectAll(Page page,ShType shType);
    /**
     * 添加一个商品类型
     */
    void addType(ShType shType);
    /*
    *  修改
    * */
    void updateType(ShType shType);
    /**
     *  删除
     */
    void deleteType(String id);
}
