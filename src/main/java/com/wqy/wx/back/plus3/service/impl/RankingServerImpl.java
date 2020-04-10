package com.wqy.wx.back.plus3.service.impl;

import com.wqy.wx.back.plus3.mapper.ShUserMapper;
import com.wqy.wx.back.plus3.service.IRankingServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: RankingServerImpl
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/10 13:44
 * @Version V1.0
 */
@Slf4j
@Service
public class RankingServerImpl implements IRankingServer {
    @Autowired
    private ShUserMapper userMapper;

    public void ranking() {

    }
}
