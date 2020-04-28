package com.wqy.wx.back.plus3.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

public interface WXPayService {
    Object wxPay(String spbill_create_ip, String out_trade_no, String openId);
}
