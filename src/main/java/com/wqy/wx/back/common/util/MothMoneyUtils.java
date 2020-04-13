package com.wqy.wx.back.common.util;

import com.wqy.wx.back.plus3.entity.MothMoney;
import com.wqy.wx.back.plus3.mapper.MothMoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class MothMoneyUtils {
    @Autowired
    private MothMoneyMapper mothMoneyMapper;

    public MothMoneyUtils(BigDecimal money,String memberId){
        MothMoney mothMoney = new MothMoney();
        mothMoney.setMemberId(memberId);
        mothMoney.setMoney(money);
        mothMoney.setStatus(0);
        mothMoneyMapper.insert(mothMoney);
    }
    public MothMoneyUtils(Integer id){
        MothMoney mothMoney = mothMoneyMapper.selectById(id);
        mothMoney.setStatus(1);
        mothMoneyMapper.updateById(mothMoney);
    }
}
