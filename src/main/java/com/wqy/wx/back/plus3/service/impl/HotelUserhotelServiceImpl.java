package com.wqy.wx.back.plus3.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.configer.annotation.PassToken;
import com.wqy.wx.back.plus3.entity.HotelUserhotel;
import com.wqy.wx.back.plus3.mapper.HotelUserhotelMapper;
import com.wqy.wx.back.plus3.service.IHotelUserhotelService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 线下店铺表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-13
 */
@Service
public class HotelUserhotelServiceImpl extends ServiceImpl<HotelUserhotelMapper, HotelUserhotel> implements IHotelUserhotelService {

}
