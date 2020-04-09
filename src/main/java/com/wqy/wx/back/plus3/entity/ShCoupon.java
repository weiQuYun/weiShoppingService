package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value = "优惠券表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShCoupon extends BaseEntity<ShCoupon> implements Serializable {

    /**
     * 优惠券ID
     */
    @ApiModelProperty(value = "优惠券ID")
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private String id;
    /**
     * 优惠券名称
     */
    @ApiModelProperty(value = "优惠券名称")
    @TableField(value = "coupon_name", fill = FieldFill.INSERT_UPDATE)
    private String couponName;
    /**
     * 优惠券数量
     */
    @ApiModelProperty(value = "优惠券数量")
    @TableField(value = "coupon_number", fill = FieldFill.INSERT_UPDATE)
    private Integer couponNumber;

}