package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.plus3.entity.ShDict;
import com.wqy.wx.back.plus3.mapper.ShDictMapper;
import com.wqy.wx.back.plus3.service.IShDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShDictServiceImpl extends ServiceImpl<ShDictMapper, ShDict> implements IShDictService {

}
