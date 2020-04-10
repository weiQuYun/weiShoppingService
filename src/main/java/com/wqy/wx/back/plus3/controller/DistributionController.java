package com.wqy.wx.back.plus3.controller;


import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.dto.Result;
import com.wqy.wx.back.dto.StatusCode;
import com.wqy.wx.back.plus3.entity.ShOrder;
import com.wqy.wx.back.plus3.service.DistributionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author licx
 * @since 2020-04-09
 */
@Api(tags = "分销上三层")
@RestController
@RequestMapping(Constant.MAPPING + "/shAttribute")
public class DistributionController {
    @Autowired
    private DistributionService distributionService;

    @PostMapping("")
    @ApiOperation("购买商品后调用此接口上三层分销")
    public Result<String> toDistribution(@RequestBody ShOrder  shOrder){
        //1.接受订单表，通过订单表获取 订单ID 及 会员ID 通过会员ID 可以查询上三层分销会员 订单表可获取详细商品 之后肯定要归到商品暂时获取
        if (!shOrder.getMemberId().isEmpty()&&!shOrder.getOrderId().isEmpty()&&shOrder.getTotalPrice().doubleValue()>0.0) {
            //先行判断会员ID 总价 订单ID 是否为空 任一空不会执行 总价为0不执行
            try {
                distributionService.toDistribution(shOrder);//成功无返回值 爆炸异常抛出
            }catch (Exception e){
                e.printStackTrace();
                return new Result<>(false,StatusCode.ERROR,"三层分销失败");
            }
        }
        return new Result<>(true,StatusCode.OK,"三层分销成功");
    }
}
