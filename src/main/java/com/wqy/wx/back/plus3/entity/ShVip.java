package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * @since 2020-04-10
 */
@ApiModel(value = "VIP表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShVip extends BaseEntity<ShVip> implements Serializable {

    /**
     * vipID uuid
     */
    @ApiModelProperty(value = "vipID uuid")
    @TableId(value = "id")
    @TableField(value = "id", fill = FieldFill.INSERT)
    private String id;
    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    @TableField(value = "member_id", fill = FieldFill.INSERT_UPDATE)
    private String memberId;
    /**
     * 会员名称即一级会员
     */
    @ApiModelProperty(value = "会员名称即一级会员")
    @TableField(value = "vip_name", fill = FieldFill.INSERT_UPDATE)
    private String vipName;
    /**
     * 梯度VIP等级由购买力定
     */
    @ApiModelProperty(value = "梯度VIP等级由购买力定")
    @TableField(value = "vip_price", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal vipPrice;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}