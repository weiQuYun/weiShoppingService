package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.plus3.entity.ShCart;
import com.wqy.wx.back.plus3.mapper.ShCartMapper;
import com.wqy.wx.back.plus3.service.IShCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShCartServiceImpl extends ServiceImpl<ShCartMapper, ShCart> implements IShCartService {

}
