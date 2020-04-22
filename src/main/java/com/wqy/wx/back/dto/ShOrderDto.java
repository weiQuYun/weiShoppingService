package com.wqy.wx.back.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShOrderDto {
    @ApiModelProperty("订单ID")
    private String shOrderId;
    @ApiModelProperty("发货状态")
    private Integer shOrderSendStatus;
    @ApiModelProperty("快递公司")
    private String shOrdercompany;
    @ApiModelProperty("快递单号")
    private String shOrderNumber;
}
