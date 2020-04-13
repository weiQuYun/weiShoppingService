package com.wqy.wx.back.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: ExchangeReqDto
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/11 18:03
 * @Version V1.0
 */
@ApiModel("兑换申请导出Dto")
@Data
public class ExchangeReqDto implements Serializable {
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;
    /**
     * 提现积分
     */
    @ApiModelProperty(value = "提现积分")
    private BigDecimal exchangeIntegral;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
