package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wqy.wx.back.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author licm
 * @since 2020-04-09
 */
@ApiModel(value = "VIP表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShVip implements Serializable {

    /**
     * vipID uuid
     */
    @ApiModelProperty(value = "vipID 自增")
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private String id;
    /**
     * 会员ID
     */
    @ApiModelProperty(value = "vip等级")
    @TableField(value = "vip_level", fill = FieldFill.INSERT_UPDATE)
    private Integer vipLevel;
    /**
     * 会员名称即一级会员
     */
    @ApiModelProperty(value = "会员名称即一级会员")
    @TableField(value = "vipName", fill = FieldFill.INSERT_UPDATE)
    private String vipName;
    /**
     * 梯度VIP等级由购买力定
     */
    @ApiModelProperty(value = "梯度VIP等级由购买力定")
    @TableField(value = "vipPrice", fill = FieldFill.INSERT_UPDATE)
    private Integer vipPrice;

}