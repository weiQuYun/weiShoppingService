package com.wqy.modules.shopping.service.impl;

import com.wqy.modules.shopping.entity.ShMember;
import com.wqy.modules.shopping.entity.ShMemberSub;
import com.wqy.modules.shopping.mapper.ShDistributionMapper;
import com.wqy.modules.shopping.service.IShDistributionService;
import com.wqy.modules.shopping.service.IShMemberService;
import com.wqy.modules.shopping.service.IShMemberSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Service("shDistributionService")
public class ShDistributionService implements ShDistributionMapper {


    @Autowired
    @Qualifier("shMemberSubServiceImpl")
    private IShMemberSubService shMemberSubService;

    @Autowired
    @Qualifier("shMemberServiceImpl")
    private IShMemberService shMemberService;



       //直推 , 返直推人员百分比注册金额  被推人会成为 该人的下级
    @Override
    public void getPushPrice(ShMember shMember1,ShMember shMember2,BigDecimal inputPrice,int rate) {
            //判断 ，被推人是否已经是 会员
        ShMember shMember=null;
           if(shMember2!=null){
               shMember= shMemberService.selectById(shMember2.getId());
           }



            if(shMember==null){
                //非会员，直推效果
                //1.将被推人新增为会员
                shMemberService.insert(shMember2);
                //2.添加关联关系表
                ShMemberSub shMemberSub=new ShMemberSub();
                shMemberSub.setShUser(shMember1);
                shMemberSub.setShUserSub(shMember2);
                shMemberSub.setIsAgent(false);



                BigDecimal returnPrice= inputPrice.multiply(new BigDecimal(rate)).divide(new BigDecimal(100));
                // 对人员返现具体操作，需微信接口，暂留








            }



    }
   //购买产品，获得相应积分且其上级上上级获得分红
    @Override
    public void byGoods(ShMember shMember, BigDecimal price) {
         ShMemberSub shMemberSub=  shMemberSubService.getSupMember(shMember); //获取用户管理表对象

               ShMember shMemberUp=   shMemberService.selectById(shMemberSub.getShUser().getId());  //上级人员


        ShMemberSub shMemberSubTwo=  shMemberSubService.getSupMember(shMemberUp); //获取用户管理表对象
        ShMember shMemberUpUp=   shMemberService.selectById(shMemberSubTwo.getShUser().getId());  //上上级人员

        //用户积分增加
        shMember.setIntegral(shMember.getIntegral()+ Double.valueOf(price.toString()));
        shMember.updateById();
        //同时进行上级分红 14%  ,上上级6% ,分红之前对上级人员进行过滤，若该上级 设置本人为分红人，则对上级及其上上级分红      否则不分红
        if(shMemberSub.getIsAgent()){
            //上级分红具体操作，需要微信接口操作 ，此时暂留 ，仅改变总分红字段
            shMemberUp.setCountReceive(shMemberUp.getCountReceive().add(price).multiply(new BigDecimal(0.14)));
            shMemberUpUp.setCountReceive(shMemberUpUp.getCountReceive().add(price).multiply(new BigDecimal(0.06)));





            shMemberUp.updateById();
            shMemberUpUp.updateById();
        }

    }

    //积分提现
    @Override
    public boolean integralToCash(ShMember shMember,int price) {
         //积分提现会返50，并且扣除5%手续费
        //首先判断是否能完成提现
        if(shMember.getIntegral()- price*2 - price*0.05>=0 ){
            //提现操作，积分减少
            shMember.setIntegral(shMember.getIntegral()- price*2 - price*0.05);
            //现金增加，需微信接口操作




              return true;
        }

        return  false;

    }

    //积分兑换商品
    @Override
    public boolean integralChange(ShMember shMember,BigDecimal goodsPrice) {
            //先查询该用户使用积分兑换次数 ,根据次数 返回兑换率
           int count= shMember.getIntegralChangeCount();
           int rate =shMember.getIntegralChangeRate();
          switch (count) {
              case 0:
                 rate=100;
                  break;
              case 1:
                 rate=70;
                  break;
                  default:
                   if(rate>54) rate-=2;
                  break;
        }
        //判断积分是否足以兑换商品
        if(new BigDecimal(shMember.getIntegral()).multiply(new BigDecimal(rate)).divide(new BigDecimal(100)).compareTo(goodsPrice)>=0 ){
            //扣除积分，更改兑换次数和兑换率
            double integral=shMember.getIntegral();
            double resultIntegral=integral- Double.valueOf(goodsPrice.divide(new BigDecimal(rate)).multiply(new BigDecimal(100)).toString());
            shMember.setIntegral(resultIntegral);
            shMember.setIntegralChangeCount(++count);
            shMember.setIntegralChangeRate(rate);
            return  true;
        }
        return false;
    }
}
