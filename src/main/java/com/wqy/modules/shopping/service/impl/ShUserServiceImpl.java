package com.wqy.modules.shopping.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
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
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

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
//        return shUserMapper.();
        return  null;
    }

    @Override
    public Page<ShUser> getUserPage(int page, int size) {
        PageHelper.startPage(page, size);
//        return (Page<ShUser>) shUserMapper.selectAll();
        return  null;
    }

    @Override
    public void addShUser(ShUser shUser) {
        shUserMapper.insert(shUser);
    }

    @Override
    public void updateShUser(ShUser shUser) {
        shUserMapper.selectById(shUser);
    }

    @Override
    public void deleteShUser(String id) {
        ShUser shUser = new ShUser();
        shUser.setId(id);
        shUserMapper.deleteById(id);
    }

    @Override
    public ShUser login(String userName, String pwd) {
        ShUser shUser = new ShUser();
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("username",userName);
        wrapper.eq("password",pwd);
        List<ShUser> list = shUser.selectList(wrapper);
//        List<ShUser> list = shUserMapper.selectList(wrapper);
        if(!CollectionUtils.isEmpty(list)&&list.size()==1){
            return list.get(0);
        }
        return null;
    }
}
