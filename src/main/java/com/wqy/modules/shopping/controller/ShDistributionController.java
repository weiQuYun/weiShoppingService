package com.wqy.modules.shopping.controller;


import com.wqy.modules.common.pojo.Result;
import com.wqy.modules.shopping.entity.ShMember;
import com.wqy.modules.shopping.mapper.ShDistributionMapper;
import com.wqy.modules.shopping.service.impl.ShMemberServiceImpl;
import org.apache.tomcat.jni.Shm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/distribution")
public class ShDistributionController {

    @Autowired
    @Qualifier("shDistributionService")
    private ShDistributionMapper shDistributionService;

    @Autowired
    @Qualifier("shMemberServiceImpl")
    private ShMemberServiceImpl shMemberServiceImpl;

    //直推有问题，暂留
     @GetMapping(value = "/push/person")
    public Result pushPerson(String pushId,String pushedId){
         shDistributionService.getPushPrice(shMemberServiceImpl.selectById(pushId),shMemberServiceImpl.selectById(pushedId),new BigDecimal("586"),30);
         return new Result(true,200,"success");
    }


    //购买商品
    @PostMapping(value = "/buy/goods")
    public Result buyGoods(@RequestParam( value = "price",required = true)String price, @RequestBody ShMember shMember){
         shDistributionService.byGoods(shMember,new BigDecimal(price));
         return new Result(true,200,"success");
    }

    //积分提现
    @PostMapping(value = "integral/cash")
    public Result integralToCash(@RequestParam(value = "price",required = true)String price, @RequestBody ShMember shMember){
         boolean result= shDistributionService.integralToCash(shMember,Integer.valueOf(price));
         return new Result(result,200,result?"success":"error");
    }

    //积分兑换
    @PostMapping(value = "/integral/change")
    public Result integralChange(@RequestParam(value = "goodsPrice",required = true)String goodsPrice,@RequestBody ShMember shMember){
         boolean result=shDistributionService.integralChange(shMember,new BigDecimal(goodsPrice));
        return new Result(result,200,result?"success":"error");
    }




}
