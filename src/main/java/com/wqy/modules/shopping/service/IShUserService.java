package com.wqy.modules.shopping.service;

import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.shopping.entity.ShUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
public interface IShUserService {

    List<ShUser> searchAll();

    Page<ShUser> getUserPage(int page, int size);

    void addShUser(ShUser shUser);

    void updateShUser(ShUser shUser);

    void deleteShUser(String id);

    ShUser login(String userName,String pwd);
}
