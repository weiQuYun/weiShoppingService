package com.zhengqing.modules.system.service.impl;

import com.zhengqing.modules.system.mapper.ShUserMapper;
import com.zhengqing.modules.system.pojo.ShUser;
import com.zhengqing.modules.system.service.ShUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShUserServiceImpl implements ShUserService {

    @Autowired
    private ShUserMapper shUserMapper;

    @Override
    public List<ShUser> getShUserService() {
        return shUserMapper.selectAll();
    }
}
