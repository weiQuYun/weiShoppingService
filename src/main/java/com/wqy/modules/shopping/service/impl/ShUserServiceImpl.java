package com.wqy.modules.shopping.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class ShUserServiceImpl extends ServiceImpl<ShUserMapper, ShUser> implements IShUserService {

    @Autowired
    private ShUserMapper shUserMapper;
    @Autowired
    private ShShareMapper shShareMapper;
    @Override
    /**
     * 添加user
     * **/
    public Result addUser(List<ShUser> list) {
        ShUser shUser1 = shUserMapper.selectOne(list.get(0));//首位新用户
        if (shUser1.getId().isEmpty()){
            return new Result(false,StatusCode.ERROR,"已存在");
        }
        //1.判断是否为推荐
        if(list.size()>1){
            //推荐
            //获取推荐人信息判断推荐人是否存在
            ShUser shareShUser = shUserMapper.selectOne(list.get(1));
            if (shareShUser.getId().isEmpty()){
                return new Result(false,StatusCode.ERROR,"推荐人不存在");
            }
            //存在推荐添加新建用户
            ShUser shUser = list.get(0);
            String charAndNumr = UUIDUtils.getCharAndNumr(32);
            shUser.setId(charAndNumr);
            shUser.setCreateTime(new Date().getTime());
            shUser.setIsActive(1);
            //添加推荐表
            ShShare shShare = new ShShare();
            shShare.setOldId(shareShUser.getId());
            shShare.setNewId(charAndNumr);
            shShareMapper.insert(shShare);
            //
            shUser.setRoleId("roleID");//4444444444444444444444444
            //新建用户
            shUserMapper.insert(shUser);
            return new Result(true,StatusCode.OK,"成功");
        }else {
            //自注册
            //2不存在新建用户
            ShUser shUser = list.get(0);
            String charAndNumr = UUIDUtils.getCharAndNumr(32);
            shUser.setId(charAndNumr);
            shUser.setCreateTime(new Date().getTime());
            shUser.setIsActive(1);
            shUser.setRoleId("roleID");//4444444444444444444444444
            //3.新建用户
            shUserMapper.insert(shUser);
            return new Result(true,StatusCode.OK,"成功");
        }
    }
    /**
     * 修改User
     * **/
}
