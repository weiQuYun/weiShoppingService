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
import java.util.Date;

/**
 * @author licm
 * @since 2020-04-10
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
    @TableId(value = "id")
    @TableField(value = "id", fill = FieldFill.INSERT)
    private String id;
    /**
     * 优惠券名称
     */
    @ApiModelProperty(value = "优惠券名称")
    @TableField(value = "coupon_name", fill = FieldFill.INSERT_UPDATE)
    private String couponName;
    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    @TableField(value = "out_time", fill = FieldFill.INSERT_UPDATE)
    private Date outTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "member_id",fill = FieldFill.INSERT_UPDATE)
    private String memberId;
    @TableField(value = "hotel_id",fill = FieldFill.INSERT_UPDATE)
    private String hotelId;
    @TableField(value = "stuts",fill = FieldFill.INSERT_UPDATE)
    private Boolean stuts=false;
}