package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.MothMoneyUtils;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShMoney;
import com.wqy.wx.back.plus3.entity.ShVip;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import com.wqy.wx.back.plus3.mapper.ShMoneyMapper;
import com.wqy.wx.back.plus3.mapper.ShVipMapper;
import com.wqy.wx.back.plus3.service.IShMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShMemberServiceImpl extends ServiceImpl<ShMemberMapper, ShMember> implements IShMemberService {
    @Autowired
    private ShMemberMapper shMemberMapper;
    @Autowired
    private ShVipMapper shVipMapper;
    @Autowired
    private ShMoneyMapper shMoneyMapper;
    @Autowired
    private MothMoneyUtils mothMoneyUtils;
    ShMoney shMoney=new ShMoney();
    @Override
    @Transactional
    public Boolean addMember(ShMember shMember) {
        if (!StringUtils.isEmpty(shMember.getParentId())) { //先判断他有没有上级，如果有进判断，如果没有直接添加设置为团长
            ShMember shMember1 = shMemberMapper.selectByParentId(shMember.getParentId());//获取他的上级
            if (shMember1.getLvVip()==0) { //判断是否是vip
                return false;
            } else { //如果是vip就添加
                String uuid = UUIDUtils.getCharAndNumr();
                shMember.setId(uuid);
                Date date = new Date();
                shMember.setCreateTime(date);
                String parentId = shMember.getParentId();
                shMemberMapper.addMember(shMember);
                shMemberMapper.updateIntegral(parentId);
                //添加完毕后添加他个人的钱包，钱包金额设置为0
                shMoney.setAmount(new BigDecimal(0));
                shMoney.setId(shMember.getId());
                shMoneyMapper.insert(shMoney);
                return true;

            }
        } else {//直接设置为团长
            String uuid = UUIDUtils.getCharAndNumr();
            shMember.setId(uuid);
            Date date = new Date();
            shMember.setCreateTime(date);
            //设置是团长
            shMember.setIfsCaptain(1);
            String parentId = shMember.getParentId();
            shMemberMapper.addMember(shMember);
            //添加完毕后添加他个人的钱包，钱包金额设置为0
            shMoney.setAmount(new BigDecimal(0));
            shMoney.setId(shMember.getId());
            shMoneyMapper.insert(shMoney);
            return true;
        }
    }

    /**
     * 新建会员
     *
     * @param lvVip
     * @param id
     */
    @Override
    @Transactional
    public void addVipMember(Integer lvVip, String id) {
        //添加vip
        shMemberMapper.addVipMember(lvVip, id);
        //根据id查找这个用户
        ShMember shMember1 = shMemberMapper.selectByid(id);
        //查询上级id
        String parentId = shMember1.getParentId();

        //查找该vip等级的金额好返点
        //===================
        ShVip shVip = shVipMapper.selectLevel(lvVip);
        //百分之30
        float num = (float) Constant.REBATES / 100;
        //百分之10
        float num2=(float) Constant.REBATES_UP/100;
        //查询vip价格
        Integer vipPrice = shVip.getVipPrice();
        if (vipPrice == null || vipPrice == 0) {
            vipPrice = 0;

        }
        //查询vip等级，好进行不同返点
        Integer vipLevel = shVip.getVipLevel();
        //将购买vip的价格加入到月钱表
        mothMoneyUtils.MothMoneyUtilss(new BigDecimal(vipPrice),id);
        //是不是一级会员
        if (vipLevel==1){
            //判断有没有上级
            if (StringUtils.isEmpty(parentId)){
                return;
            }else {
                ShMember shMember2 = shMemberMapper.selectByid(parentId);
                Long integral = null;
                if (shMember2.getIntegral()==null){
                    integral=0l;
                }else{
                    integral=shMember2.getIntegral();
                }
                Long fist = this.fist(integral, num, vipPrice);
                this.rebatesIntegral(fist,parentId);
            }
            //二级会员进这个判断
        }else if (vipLevel==2){
            if (StringUtils.isEmpty(parentId)){ //如果上级为空
                return;
            }
            //查询上级
            ShMember shMember = shMemberMapper.selectByid(parentId);
            //查询他的上上级的id
            String parentId2 = shMember.getParentId();
            ShMember shMember3 = shMemberMapper.selectByid(parentId2);
          //  String parentId3 = shMember3.getParentId();
            //System.out.println("==============================");
            System.out.println(shMember3==null);
            //System.out.println(shMember3==null);
            if (StringUtils.isEmpty(parentId)){
                return;
            }else if (!StringUtils.isEmpty(parentId) && StringUtils.isEmpty(parentId2) && shMember3==null){ //只有一层
                ShMember shMember2 = shMemberMapper.selectByid(parentId);
                Long integral = shMember2.getIntegral();
                Long fist = this.fist(integral, num, vipPrice);
                this.rebatesIntegral(fist,parentId);
            }else if (!StringUtils.isEmpty(parentId) && !StringUtils.isEmpty(parentId2) && shMember3==null){ //有两层
                ShMember shMember2 = shMemberMapper.selectByid(parentId2);
                Long integral = shMember2.getIntegral();
                Long fist = this.fist(integral, num, vipPrice);
                Long second=this.fist(integral,num2,vipPrice);
                this.rebatesIntegral(fist,parentId);
                this.rebatesIntegral(second,parentId2);
            }else if (!StringUtils.isEmpty(parentId) && !StringUtils.isEmpty(parentId2) && shMember3!=null){ //有三层
                String parentId4 = shMemberMapper.selectByid(parentId2).getParentId();
                ShMember shMember2 = shMemberMapper.selectByid(parentId2);
                Long integral = shMember2.getIntegral();
                Long fist = this.fist(integral, num, vipPrice);
                Long second=this.fist(integral,num2,vipPrice);
                this.rebatesIntegral(fist,parentId);
                this.rebatesIntegral(second,parentId2);
                this.rebatesIntegral(second,parentId4);

            }
        }

    }

    @Override
    public ShMember selectById(String id) {
        return shMemberMapper.selectByid(id);
    }

    /**
     * 积分返点
     *
     * @param integral
     * @param id
     */
    @Override
    @Transactional
    public void rebatesIntegral(Long integral, String id) {
        shMemberMapper.rebatesIntegral(integral, id);
    }

    /**
     * 计算折算后的积分
     * @param integral  积分
     * @param num  百分率
     * @param vipPrice  vip价格
     * @return
     */
    public Long fist(Long integral,float num,Integer vipPrice){
        int price = vipPrice.intValue();
        float v = price * num;
        int i = (int) v;
        Integer integral1 = new Integer(i);
        //System.out.println("=============");
        Long integrals = integral + integral1;
        //System.out.println(integral);
        // System.out.println(id1);
        // System.out.println(shMember.getIntegral());
        return integrals;
        }

}
