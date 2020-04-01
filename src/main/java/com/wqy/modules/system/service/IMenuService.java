package com.wqy.modules.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wqy.modules.system.dto.input.MenuQueryPara;
import com.wqy.modules.system.entity.Menu;

import java.util.List;

/**
 * <p>  系统管理-菜单表  服务类 </p>
 *
 * @author: wqy
 * @date: 2019-08-19
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 菜单树
     *
     * @param :
     * @return: java.util.List<com.wqy.modules.system.entity.Menu>
     */
    List<Menu> listTreeMenu();

    /**
     * 系统管理-菜单表 列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    void listPage(Page<Menu> page, MenuQueryPara filter);

    /**
     * 保存系统管理-菜单表
     *
     * @param input
     */
    Integer save(Menu input);

    /**
     * 系统管理-菜单表 列表
     *
     * @param filter
     * @return
     */
    List<Menu> list(MenuQueryPara filter);
}
