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
import java.math.BigDecimal;

/**
 * @author licm
 * @since 2020-04-03
 */
@ApiModel(value = "成功订单页面")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TOrderInfo extends BaseEntity<TOrderInfo> implements Serializable {

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    @TableField(value = "order_id", fill = FieldFill.INSERT_UPDATE)
    private String orderId;
    /**
     * 产品ID
     */
    @ApiModelProperty(value = "产品ID")
    @TableField(value = "product_id", fill = FieldFill.INSERT_UPDATE)
    private String productId;
    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额")
    @TableField(value = "receivable_amount", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal receivableAmount;
    /**
     * 实收金额
     */
    @ApiModelProperty(value = "实收金额")
    @TableField(value = "received_amount", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal receivedAmount;
    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    @TableField(value = "discount_amount", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "商品数量")
    @TableField(value = "product_number", fill = FieldFill.INSERT_UPDATE)
    private Integer productNumber;
    @TableField(exist = false)
    private TProduct tProduct;

}