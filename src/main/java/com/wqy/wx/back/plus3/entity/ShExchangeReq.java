package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wqy.wx.back.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author licm
 * @since 2020-04-11
 */
@ApiModel(value = "积分兑换申请表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShExchangeReq extends BaseEntity<ShExchangeReq> implements Serializable {

    /**
     * 自动主键
     */
    @ApiModelProperty(value = "自动主键")
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField(value = "phone", fill = FieldFill.INSERT_UPDATE)
    private String phone;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableField(value = "member_id", fill = FieldFill.INSERT_UPDATE)
    private String memberId;
    /**
     * 提现积分
     */
    @ApiModelProperty(value = "提现积分")
    @TableField(value = "exchange_integral", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal exchangeIntegral;
    /**
     * 申请状态  0 未处理 1 已受理
     */
    @ApiModelProperty(value = "申请状态  0 未处理 1 已受理")
    @TableField(value = "status", fill = FieldFill.INSERT_UPDATE)
    private Integer status;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}