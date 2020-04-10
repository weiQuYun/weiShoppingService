package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.plus3.entity.ShGoodsAttr;
import com.wqy.wx.back.plus3.mapper.ShGoodsAttrMapper;
import com.wqy.wx.back.plus3.service.IShGoodsAttrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品详情表用于商品页面的商品详情 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShGoodsAttrServiceImpl extends ServiceImpl<ShGoodsAttrMapper, ShGoodsAttr> implements IShGoodsAttrService {

}
