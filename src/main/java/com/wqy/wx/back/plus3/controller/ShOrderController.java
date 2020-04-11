package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.ShCart;
import com.wqy.wx.back.plus3.entity.ShOrder;
import com.wqy.wx.back.plus3.service.IShOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "订单表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/order")
public class ShOrderController {
    //每次订单成功之后判断 如果商品购买量大于XXX 将其变为热销

   @Autowired
   private IShOrderService iShOrderService;
    @PostMapping("")
    @ApiOperation(value = "订单生成接口")
    public ShOrder getShOrder(@RequestBody ShCart shCart){
        //通过购物车生成订单返回
        ShOrder shOrder = iShOrderService.insertShOrder(shCart);
        return shOrder;
    }
    @PutMapping("")
    @ApiOperation("正式下单或者改变订单")
    public String updateShOrder(@RequestBody ShOrder shOrder){
        iShOrderService.updateShOrder(shOrder);
        return null;
    }
    @GetMapping("/{id}")
    @ApiOperation("这是请求获取订单的接口")
    public ShOrder getOrder(@PathVariable String id){
        return iShOrderService.selectByShOrderId(id);
    }


}
