package com.wqy.wx.back.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: MonthDto
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/13 14:56
 * @Version V1.0
 */
@ApiModel("消费记录")
@Data
public class MonthDto implements Serializable {
    @ApiModelProperty("用户id")
    private String memberId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("消费时间")
    private Date createTime;
    @ApiModelProperty("String时间")
    private String addTime;
    @ApiModelProperty("消费金额")
    private BigDecimal money;
    @ApiModelProperty("状态")
    private String  status;

}
