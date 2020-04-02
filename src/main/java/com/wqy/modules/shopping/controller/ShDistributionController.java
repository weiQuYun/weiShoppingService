package com.wqy.modules.shopping.controller;


import com.wqy.modules.common.pojo.Result;
import com.wqy.modules.shopping.mapper.ShDistributionMapper;
import com.wqy.modules.shopping.service.impl.ShMemberServiceImpl;
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

     @GetMapping(value = "/push/person")
    public Result pushPerson(String pushId,String pushedId){
         shDistributionService.getPushPrice(shMemberServiceImpl.selectById(pushId),shMemberServiceImpl.selectById(pushedId),new BigDecimal("586"),30);
         return new Result(true,200,"success");
    }



}
