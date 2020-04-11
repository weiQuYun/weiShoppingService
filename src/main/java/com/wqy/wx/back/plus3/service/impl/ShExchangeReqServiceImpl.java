package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.plus3.entity.ShExchangeReq;
import com.wqy.wx.back.plus3.mapper.ShExchangeReqMapper;
import com.wqy.wx.back.plus3.service.IShExchangeReqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 积分兑换申请表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-11
 */
@Slf4j
@Primary
@Service
public class ShExchangeReqServiceImpl extends ServiceImpl<ShExchangeReqMapper, ShExchangeReq> implements IShExchangeReqService {

}