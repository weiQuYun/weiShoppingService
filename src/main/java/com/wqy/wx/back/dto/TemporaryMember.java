package com.wqy.wx.back.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "滚动条数据")
@Data
public class TemporaryMember<T> {

    @ApiModelProperty(value = "子会员")
    private String memberSon;
    @ApiModelProperty(value = "父会员")
    private String memberParent;
    @ApiModelProperty(value = "更多参数具体是啥我也不知道")
    private T data;
}
