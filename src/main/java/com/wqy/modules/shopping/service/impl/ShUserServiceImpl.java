package com.wqy.modules.shopping.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.common.pojo.Result;
import com.wqy.modules.common.pojo.StatusCode;
import com.wqy.modules.shopping.entity.ShRole;
import com.wqy.modules.shopping.entity.ShShare;
import com.wqy.modules.shopping.entity.ShUser;
import com.wqy.modules.shopping.mapper.ShShareMapper;
import com.wqy.modules.shopping.mapper.ShUserMapper;
import com.wqy.modules.shopping.service.IShUserService;
import com.wqy.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
@Service
public class ShUserServiceImpl implements IShUserService {

    @Autowired
    private ShUserMapper shUserMapper;

    @Override
    public List<ShUser> searchAll() {
        return shUserMapper.selectAll();
    }

    @Override
    public Page<ShUser> getUserPage(int page, int size) {
        PageHelper.startPage(page, size);
        return (Page<ShUser>) shUserMapper.selectAll();
    }

    @Override
    public void addShUser(ShUser shUser) {
        shUserMapper.insert(shUser);
    }

    @Override
    public void updateShUser(ShUser shUser) {
        shUserMapper.updateByPrimaryKey(shUser);
    }

    @Override
    public void deleteShUser(String id) {
        ShUser shUser = new ShUser();
        shUser.setId(id);
        shUserMapper.delete(shUser);
    }
}
