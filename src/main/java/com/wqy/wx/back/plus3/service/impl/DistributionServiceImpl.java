package com.wqy.wx.back.plus3.service.impl;


import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShOrder;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import com.wqy.wx.back.plus3.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * <p>
 * 属性表 服务实现类
 * </p>
 *
 * @author licx
 * @since 2020-04-09
 */


/**
 * 此method动过积分表！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * **/
@Service
public class DistributionServiceImpl implements DistributionService {
    @Autowired
    private ShMemberMapper shMemberMapper;
    @Override
    public void toDistribution(ShOrder shOrder) throws Exception {
        //1.获取userID 向上三层循环查询 其查询method抽出查询
        String memberId = shOrder.getMemberId();
        ShMember shMember = new ShMember();
        shMember.setId(memberId);
        Object od = threeSearchMenber(shMember);
        //传递对象查询方便之后还有使用

    }
    private Object threeSearchMenber(ShMember shMemberOld){
        String id = shMemberOld.getId();
        for (int i = 0; i < 3 ; i++) {
            ShMember shMember = shMemberMapper.selectById(id);//通过父ID查询上一层
            if (shMember==null){
                //说明没有父ID 直接返回
                break;
            }
            //有父ID
        }
        return null;
    }
}
