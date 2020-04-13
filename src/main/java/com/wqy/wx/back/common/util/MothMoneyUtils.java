package com.wqy.wx.back.common.util;

import com.wqy.wx.back.plus3.entity.MothMoney;
import com.wqy.wx.back.plus3.mapper.MothMoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MothMoneyUtils {
    @Autowired
    private MothMoneyMapper mothMoneyMapper;

    public MothMoneyUtils() {
    }

    public void MothMoneyUtilss(BigDecimal money, String memberId){
        MothMoney mothMoney = new MothMoney();
        mothMoney.setMemberId(memberId);
        mothMoney.setMoney(money);
        mothMoney.setStatus(0);
        mothMoney.setId(0);

        mothMoneyMapper.insert(mothMoney);
    }
    public void MothMoneyUtilss(Integer id){
        MothMoney mothMoney = mothMoneyMapper.selectById(id);
        mothMoney.setStatus(1);
        mothMoneyMapper.updateById(mothMoney);
    }
}
