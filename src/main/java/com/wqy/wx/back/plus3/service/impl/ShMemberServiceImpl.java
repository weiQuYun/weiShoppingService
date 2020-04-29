package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.MothMoneyUtils;
import com.wqy.wx.back.common.util.OpenIdGetUtils;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.dto.WxPatDto;
import com.wqy.wx.back.plus3.entity.*;
import com.wqy.wx.back.plus3.mapper.*;
import com.wqy.wx.back.plus3.service.IShMemberService;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ShCouponMapper shCouponMapper;
    @Autowired
    private ShareIDChange shareIDChange;
    @Autowired
    private ShOrderMapper shOrderMapper;
    ShMoney shMoney=new ShMoney();
    @Override
    @Transactional
    public Boolean addMember(ShMember shMember) {
        String id = shMember.getId();
        if (shMemberMapper.selectById(id)!=null) {
            if (!StringUtils.isEmpty(shMember.getParentId())) { //先判断他有没有上级，如果有进判断，如果没有直接添加设置为团长
                ShMember shMember1 = shMemberMapper.selectByParentId(shMember.getParentId());//获取他的上级
                if (shMember1.getLvVip() == 0) { //判断是否是vip
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
                    //shareIDChange.insertParentLongSortId(parentId);
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
               // shareIDChange.insertParentLongSortId(parentId);
                return true;
            }
        }else {
            return false;
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
    public void

    addVipMember(Integer lvVip, String id) {
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
            //添加优惠卷
            ShCoupon shCoupon = new ShCoupon();
            //添加会员id
           shCoupon.setMemberId(id);
            //添加UUID
           shCoupon.setId(UUIDUtils.getCharAndNumr());
           //添加优惠一袋水
            shCoupon.setCouponName("水");

            shCouponMapper.insert(shCoupon);
            //判断有没有上级
            if (StringUtils.isEmpty(parentId)){
                return;
            }else {
                ShMember shMember2 = shMemberMapper.selectByid(parentId);
                //添加优惠卷
                ShCoupon shCoupon2 = new ShCoupon();
                //添加会员id
                shCoupon2.setMemberId(parentId);
                //添加UUID
                shCoupon2.setId(UUIDUtils.getCharAndNumr());
                //添加线下优惠卷
                shCoupon2.setCouponName("线下体验卷");
                shCouponMapper.insert(shCoupon);
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
        }
        else if (vipLevel==2){
            //添加优惠卷
            ShCoupon shCoupon = new ShCoupon();
            //添加会员id
            shCoupon.setMemberId(id);
            for (int i = 0; i <3; i++) {
                //添加优惠卷
                ShCoupon shCoupon1 = new ShCoupon();
                //添加会员id
                shCoupon1.setMemberId(id);
                //添加UUID
                shCoupon1.setId(UUIDUtils.getCharAndNumr());
                shCoupon1.setCouponName("水");
                shCouponMapper.insert(shCoupon1);
            }
            if (StringUtils.isEmpty(parentId)){ //如果上级为空
                return;
            }
            //查询上级
            ShMember shMember = shMemberMapper.selectByid(parentId);
            System.out.println("---------------");
            System.out.println(shMember.getParentId()==" ");
            System.out.println(StringUtils.isEmpty(parentId));
            //查询他的上上级的id
            String parentId2 = shMember.getParentId();
            System.out.println(parentId2.equals(""));
            ShMember shMember3 = shMemberMapper.selectByid(parentId2);
          //  String parentId3 = shMember3.getParentId();
           // parentId2.equals("")
            System.out.println("==============================");
            System.out.println(shMember3);
            //System.out.println(shMember3==null);
            if (StringUtils.isEmpty(parentId)){
                return;
            }else if (!StringUtils.isEmpty(parentId) && StringUtils.isEmpty(parentId2) && shMember3==null){
                System.out.println("11111111111111111");//只有一层
                //添加4优惠卷
                for (int i = 0; i <4; i++) {
                    //添加优惠卷
                    ShCoupon shCoupon1 = new ShCoupon();
                    //添加会员id
                    shCoupon1.setMemberId(parentId);
                    //添加UUID
                    shCoupon1.setId(UUIDUtils.getCharAndNumr());
                    shCoupon1.setCouponName("线下优惠卷");
                    shCouponMapper.insert(shCoupon1);
                }
                ShMember shMember2 = shMemberMapper.selectByid(parentId);
                Long integral = shMember2.getIntegral();
                Long fist = this.fist(integral, num, vipPrice);
                this.rebatesIntegral(fist,parentId);
            }else if (!StringUtils.isEmpty(parentId) && !StringUtils.isEmpty(parentId2) && shMember3==null){ //有两层
                //添加4优惠卷
                for (int i = 0; i <4; i++) {
                    //添加优惠卷
                    ShCoupon shCoupon1 = new ShCoupon();
                    //添加会员id
                    shCoupon1.setMemberId(parentId);
                    //添加UUID
                    shCoupon1.setId(UUIDUtils.getCharAndNumr());
                    shCoupon1.setCouponName("线下优惠卷");
                    shCouponMapper.insert(shCoupon1);
                }
                ShMember shMember2 = shMemberMapper.selectByid(parentId2);
                Long integral = shMember2.getIntegral();
                Long fist = this.fist(integral, num, vipPrice);
                Long second=this.fist(integral,num2,vipPrice);
                this.rebatesIntegral(fist,parentId);
                this.rebatesIntegral(second,parentId2);
            }else if (!StringUtils.isEmpty(parentId) && !StringUtils.isEmpty(parentId2) && shMember3!=null){ //有三层
                //添加4优惠卷
                for (int i = 0; i <4; i++) {
                    //添加优惠卷
                    ShCoupon shCoupon1 = new ShCoupon();
                    //添加会员id
                    shCoupon1.setMemberId(parentId);
                    //添加UUID
                    shCoupon1.setId(UUIDUtils.getCharAndNumr());
                    shCoupon1.setCouponName("线下优惠卷");
                    shCouponMapper.insert(shCoupon1);
                }
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
        else if (lvVip==3){
            //添加优惠卷
            ShCoupon shCoupon = new ShCoupon();
            //添加会员id
            shCoupon.setMemberId(id);
            for (int i = 0; i <6; i++) {
                //添加优惠卷
                ShCoupon shCoupon1 = new ShCoupon();
                //添加会员id
                shCoupon1.setMemberId(id);
                //添加UUID
                shCoupon1.setId(UUIDUtils.getCharAndNumr());
                shCoupon1.setCouponName("水");
                shCouponMapper.insert(shCoupon1);
            }
            if (StringUtils.isEmpty(parentId)){ //如果上级为空
                return;
            }
            //查询上级
            ShMember shMember = shMemberMapper.selectByid(parentId);
            //查询他的上上级的id
            String parentId2 = shMember.getParentId();
            ShMember shMember3 = shMemberMapper.selectByid(parentId2);
            //  String parentId3 = shMember3.getParentId();
            System.out.println("==============================");
            System.out.println(shMember3==null);
            System.out.println(shMember3);
            //System.out.println(shMember3==null);
            if (StringUtils.isEmpty(parentId)){
                return;
            }else if (!StringUtils.isEmpty(parentId) && StringUtils.isEmpty(parentId2) && shMember3==null){ //只有一层
                //添加12优惠卷
                for (int i = 0; i <12; i++) {
                    //添加优惠卷
                    ShCoupon shCoupon1 = new ShCoupon();
                    //添加会员id
                    shCoupon1.setMemberId(parentId);
                    //添加UUID
                    shCoupon1.setId(UUIDUtils.getCharAndNumr());
                    shCoupon1.setCouponName("线下优惠卷");
                    shCouponMapper.insert(shCoupon1);
                }
                ShMember shMember2 = shMemberMapper.selectByid(parentId); //查询他的上一层对象
                Long integral = shMember2.getIntegral(); //获取他上一层的积分
                Long fist = this.fist(integral, num, vipPrice); //计算折算后的积分
                this.rebatesIntegral(fist,parentId);//积分返点
            }else if (!StringUtils.isEmpty(parentId) && !StringUtils.isEmpty(parentId2) && shMember3==null){ //有两层
                //添加12优惠卷
                for (int i = 0; i <12; i++) {
                    //添加优惠卷
                    ShCoupon shCoupon1 = new ShCoupon();
                    //添加会员id
                    shCoupon1.setMemberId(parentId);
                    //添加UUID
                    shCoupon1.setId(UUIDUtils.getCharAndNumr());
                    shCoupon1.setCouponName("线下优惠卷");
                    shCouponMapper.insert(shCoupon1);
                }
                ShMember shMember2 = shMemberMapper.selectByid(parentId2);
                Long integral = shMember2.getIntegral();
                Long fist = this.fist(integral, num, vipPrice);
                Long second=this.fist(integral,num2,vipPrice);
                this.rebatesIntegral(fist,parentId);
                this.rebatesIntegral(second,parentId2);
            }else if (!StringUtils.isEmpty(parentId) && !StringUtils.isEmpty(parentId2) && shMember3!=null){ //有三层
                //添加12优惠卷
                for (int i = 0; i <12; i++) {
                    //添加优惠卷
                    ShCoupon shCoupon1 = new ShCoupon();
                    //添加会员id
                    shCoupon1.setMemberId(parentId);
                    //添加UUID
                    shCoupon1.setId(UUIDUtils.getCharAndNumr());
                    shCoupon1.setCouponName("线下优惠卷");
                    shCouponMapper.insert(shCoupon1);
                }
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
        else if (lvVip==4){
            //添加优惠卷
            ShCoupon shCoupon = new ShCoupon();
            //添加会员id
            shCoupon.setMemberId(id);
            for (int i = 0; i <6; i++) {
                //添加优惠卷
                ShCoupon shCoupon1 = new ShCoupon();
                //添加会员id
                shCoupon1.setMemberId(id);
                //添加UUID
                shCoupon1.setId(UUIDUtils.getCharAndNumr());
                shCoupon1.setCouponName("水");
                shCouponMapper.insert(shCoupon1);
            }
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
                //添加12优惠卷
                for (int i = 0; i <12; i++) {
                    //添加优惠卷
                    ShCoupon shCoupon1 = new ShCoupon();
                    //添加会员id
                    shCoupon1.setMemberId(parentId);
                    //添加UUID
                    shCoupon1.setId(UUIDUtils.getCharAndNumr());
                    shCoupon1.setCouponName("线下优惠卷");
                    shCouponMapper.insert(shCoupon1);
                }
                //添加一个桶
                ShCoupon shCoupon2 = new ShCoupon();
                shCoupon2.setMemberId(parentId);
                //添加UUID
                shCoupon2.setId(UUIDUtils.getCharAndNumr());
                shCoupon2.setCouponName("桶");
                shCouponMapper.insert(shCoupon2);
                ShMember shMember2 = shMemberMapper.selectByid(parentId);
                Long integral = shMember2.getIntegral();
                Long fist = this.fist(integral, num, vipPrice);
                this.rebatesIntegral(fist,parentId);
            }else if (!StringUtils.isEmpty(parentId) && !StringUtils.isEmpty(parentId2) && shMember3==null){ //有两层
                //添加12优惠卷
                for (int i = 0; i <12; i++) {
                    //添加优惠卷
                    ShCoupon shCoupon1 = new ShCoupon();
                    //添加会员id
                    shCoupon1.setMemberId(parentId);
                    //添加UUID
                    shCoupon1.setId(UUIDUtils.getCharAndNumr());
                    shCoupon1.setCouponName("线下优惠卷");
                    shCouponMapper.insert(shCoupon1);
                }
                //添加一个桶
                ShCoupon shCoupon2 = new ShCoupon();
                shCoupon2.setMemberId(parentId);
                //添加UUID
                shCoupon2.setId(UUIDUtils.getCharAndNumr());
                shCoupon2.setCouponName("桶");
                shCouponMapper.insert(shCoupon2);
                ShMember shMember2 = shMemberMapper.selectByid(parentId2);
                Long integral = shMember2.getIntegral();
                Long fist = this.fist(integral, num, vipPrice);
                Long second=this.fist(integral,num2,vipPrice);
                this.rebatesIntegral(fist,parentId);
                this.rebatesIntegral(second,parentId2);
            }else if (!StringUtils.isEmpty(parentId) && !StringUtils.isEmpty(parentId2) && shMember3!=null){ //有三层
                //添加12优惠卷
                for (int i = 0; i <12; i++) {
                    //添加优惠卷
                    ShCoupon shCoupon1 = new ShCoupon();
                    //添加会员id
                    shCoupon1.setMemberId(parentId);
                    //添加UUID
                    shCoupon1.setId(UUIDUtils.getCharAndNumr());
                    shCoupon1.setCouponName("线下优惠卷");
                    shCouponMapper.insert(shCoupon1);
                }
                //添加一个桶
                ShCoupon shCoupon2 = new ShCoupon();
                shCoupon2.setMemberId(parentId);
                //添加UUID
                shCoupon2.setId(UUIDUtils.getCharAndNumr());
                shCoupon2.setCouponName("桶");
                shCouponMapper.insert(shCoupon2);
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

    /**
     * 查询单个用户
     * @param id
     * @return
     */
    @Override
    public ShMember selectById(String id) {
        System.out.println(id);
        ShMember shMember = shMemberMapper.selectByid(id);
        String id1 = shMember.getId();
        ShMoney shMoney = shMoneyMapper.selecyByMemberId(id1);
        shMember.setShMoney(shMoney);
        System.out.println("===========");
        System.out.println(shMember);
        return shMember;
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
     * 查询全部会员
     * @param
     * @return
     */
    @Override
    public Page<ShMember> selectAll(PageDTO pageDTO,ShMember shMember) {
        QueryWrapper<ShMember> queryMrapper = new QueryWrapper<ShMember>();
        QueryWrapper<ShMember> reflect = ParamUtils.reflect(shMember, queryMrapper);
        System.out.println(reflect);
        Page<ShMember> PageShMembers = new Page<>(pageDTO.getPageIndex(), pageDTO.getPageSize());
        return shMemberMapper.selectPage(PageShMembers,reflect);
    }

    @Override
    @Transactional
    //TODO
    public Boolean addMembers(String code, String parentId) {
        try {
            Map<String, String> map = OpenIdGetUtils.getOpenId(code);
            String openId = map.get("openId");
            if (shMemberMapper.selectByOpenId(openId)==null){
                if (!StringUtils.isEmpty(parentId)){ //先判断他有没有上级，如果有进判断，如果没有直接添加设置为团长
                    ShMember shMember1 = shMemberMapper.selectByShare(parentId);//获取他的上级 通过ShareNumber虚拟父id查询
                    if (shMember1.getLvVip()==0) { //判断是否是vip
                        return false;
                    } else { //如果是vip就添加
                        int parent =Integer.valueOf(UUIDUtils.getNumber(8)) ;
                        ShMember shMember = new ShMember();
                        String uuid = UUIDUtils.getCharAndNumr();
                        shMember.setShareNumber(parent);
                        shMember.setId(uuid);
                        shMember.setParentId(parentId);
                        shMember.setOpenid(openId);
                        shMember.setUsername("新用户" + System.currentTimeMillis());
                        shMember.setPassword("");
                        shMember.setEmail("");
                        shMember.setPhone("");
                        shMember.setIntegral(0L);
                        shMember.setIntegralChangeRate(0);
                        shMember.setIntegralChangeCount(0);
                        shMember.setCaptainName("");
                        Date date = new Date();
                        shMember.setCreateTime(date);
                        shMemberMapper.addMember(shMember);
                        shMemberMapper.updateIntegral(parentId);
                        //添加完毕后添加他个人的钱包，钱包金额设置为0
                        shMoney.setAmount(new BigDecimal(0));
                        shMoney.setId(shMember.getId());
                        shMoneyMapper.insert(shMoney);
                       // shareIDChange.insertParentLongSortId(parentId);
                        return true;

                    }
                } else {//直接设置为团长
                    ShMember shMember = new ShMember();
                    int parent =Integer.valueOf(UUIDUtils.getNumber(8)) ;
                    String uuid = UUIDUtils.getCharAndNumr();
                    shMember.setShareNumber(parent); //设置虚拟父id
                    shMember.setId(uuid);
                    shMember.setOpenid(openId);
                    shMember.setUsername("新用户" + System.currentTimeMillis());
                    shMember.setPassword("");
                    shMember.setEmail("");
                    shMember.setPhone("");
                    shMember.setIntegral(0L);
                    shMember.setIntegralChangeRate(0);
                    shMember.setIntegralChangeCount(0);
                    shMember.setCaptainName("");
                    Date date = new Date();
                    shMember.setCreateTime(date);
                    //设置是团长
                    shMember.setIfsCaptain(1);
                    shMemberMapper.addMember(shMember);
                    //添加完毕后添加他个人的钱包，钱包金额设置为0
                    shMoney.setAmount(new BigDecimal(0));
                    shMoney.setId(shMember.getId());
                    shMoneyMapper.insert(shMoney);
                  //  shareIDChange.insertParentLongSortId(parentId);
                    return true;
                }
            }else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       return false;
    }

    @Override
    public List<ShMember> selectDownThree(String id) {
        //一成
        List<ShMember> shMembers = shMemberMapper.selectDownThree(id);
        List<String> list = new ArrayList<>();
        for (ShMember shMember : shMembers) {
            list.add(shMember.getId());
        }
        //二成
        List<String> list1 = new ArrayList<>();
        for (String s : list) {
            List<ShMember> shMembers1 = shMemberMapper.selectDownThree(s);
            for (ShMember shMember : shMembers1) {
                list1.add(shMember.getId());
            }
        }
        //三成
        List<String> list2 = new ArrayList<>();
        for (String s : list1) {
            List<ShMember> shMembers1 = shMemberMapper.selectDownThree(s);
            for (ShMember shMember : shMembers1) {
                list2.add(shMember.getId());
            }
        }
        for (String s : list1) {
            list.add(s);
        }
        for (String s : list2) {
            list.add(s);
        }
        List<ShMember> list3 = new ArrayList<>();
        for (String s : list) {
            ShMember shMember = shMemberMapper.selectByid(s);
            list3.add(shMember);
        }
        return list3;

    }

    /**
     * 生成vip订单
     * @param shMember
     * @return
     */
    @Override
    @Transactional
    public WxPatDto vipOrder(ShMember shMember) {
            //生成vip订单
            ShOrder shOrder1 = new ShOrder();
            //生成订单号
            String orderUUID = UUIDUtils.getCharAndNumr();
            shOrder1.setOrderId(orderUUID);
            shOrder1.setMemberId(shMember.getId());
            Integer lvVip = shMember.getLvVip();
            if (lvVip==1){
                shOrder1.setTotalPrice(new BigDecimal(99));
            }
            if (lvVip==2){
                shOrder1.setTotalPrice(new BigDecimal(399));
            }
            if (lvVip==3){
                shOrder1.setTotalPrice(new BigDecimal(599));
            }
            if (lvVip==4){
                shOrder1.setTotalPrice(new BigDecimal(699));
            }
            //插入数据库
            shOrderMapper.insert(shOrder1);
        WxPatDto wxPatDto = new WxPatDto();
        wxPatDto.setId(shMember.getId());
        wxPatDto.setOrderId(orderUUID);
        return wxPatDto;


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
