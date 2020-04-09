package com.wqy.wx.back.plus2.entity;

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
 * @since 2020-04-03
 */
@ApiModel(value = "店铺表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class THotel extends BaseEntity<THotel> implements Serializable {

    /**
     * 店铺名
     */
    @ApiModelProperty(value = "店铺名")
    @TableField(value = "hotel_name", fill = FieldFill.INSERT_UPDATE)
    private String hotelName;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField(value = "product_id", fill = FieldFill.INSERT_UPDATE)
    private String productId;
    /**
     * 店铺描述
     */
    @ApiModelProperty(value = "店铺描述")
    @TableField(value = "hotel_test", fill = FieldFill.INSERT_UPDATE)
    private String hotelTest;

}