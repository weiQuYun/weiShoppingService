package com.wqy.wx.back.plus2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus2.entity.TUser;
import com.wqy.wx.back.plus2.mapper.TUserMapper;
import com.wqy.wx.back.plus2.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 后台用户 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
@Primary
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public List<TUser> searchAll() {
        return tUserMapper.selectList(null);
    }

    /**
     * 删除后台用户
     **/
    @Override
    @Transactional
    public Boolean deleteTUser(String id) {
        if (id.isEmpty()) {
            return false;
        }//实际中ID为UUID
        tUserMapper.deleteById(id);
        return true;
    }

    /**
     * 新增后台用户
     **/
    @Override
    @Transactional
    public Boolean insertTUser(TUser tUser) {
        if (tUserMapper.selectByUserName(tUser.getUserName()).size() < 1) {
            tUser.setId(UUIDUtils.getCharAndNumr());//此处添加UUID
            tUserMapper.insert(tUser);
            return true;
        } else return false;
    }

    /**
     * 修改后台用户
     **/
    @Override
    @Transactional
    public Boolean updateTUser(TUser tUser) {
        tUserMapper.updateById(tUser);
        return true;
    }

    /**
     * 分页查询 此处默认如果分页低于 1-5 默认使用1-20
     **/
    @Override
    public Page<TUser> searchAll(int page, int size, TUser tUser) {
        if (page > 0 && size > 5) {
            QueryWrapper<TUser> queryMrapper = new QueryWrapper<TUser>();
            QueryWrapper<TUser> reflect = ParamUtils.reflect(tUser, queryMrapper);
            return tUserMapper.selectPage(new Page<>(page, size), reflect);
        } else {
            QueryWrapper<TUser> queryMrapper = new QueryWrapper<TUser>();
            QueryWrapper<TUser> reflect = ParamUtils.reflect(tUser, queryMrapper);
            return tUserMapper.selectPage(new Page<>(1, 20), reflect);
        }
    }

    /**
     * 以下方法暂时感觉不到用得上未实装
     **/
    @Override
    @Transactional
    public Boolean insertTUser(List<TUser> list) {
        return true;
    }

    @Override
    @Transactional
    public Boolean updateTUser(List<TUser> list) {
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteTUser(List<String> id) {
        return true;
    }
}
