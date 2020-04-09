package com.wqy.wx.back.plus2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus2.entity.TRole;

import java.util.List;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface ITRoleService extends IService<TRole> {

    List<TRole> searchAll();

    void deleteTRole(String id);

    void insertTRole(TRole tRole);

    void updateTRole(TRole tRole);

    void searchAll(int page, int size);

    void updateTRole(List<TRole> list);

    void insertTRole(List<TRole> list);

    void deleteTRole(List<String> id);
}
