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

/**
 * @author licm
 * @since 2020-04-09
 */
@ApiModel(value = "订单商品表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShOrderGoods implements Serializable {

    /**
     * 无意义id
     */
    @ApiModelProperty(value = "无意义id")
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private Integer id;
    /**
     * 订单ID UUID
     */
    @ApiModelProperty(value = "订单ID UUID")
    @TableField(value = "order_id", fill = FieldFill.INSERT_UPDATE)
    private String orderId;
    /**
     * 商品ID UUID
     */
    @ApiModelProperty(value = "商品ID UUID")
    @TableField(value = "goods_id", fill = FieldFill.INSERT_UPDATE)
    private String goodsId;
    /**
     * 商品属性UUID
     */
    @ApiModelProperty(value = "商品属性UUID")
    @TableField(value = "goods_attr_ids", fill = FieldFill.INSERT_UPDATE)
    private String goodsAttrIds;
    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    @TableField(value = "goods_number", fill = FieldFill.INSERT_UPDATE)
    private Integer goodsNumber;
    /**
     * 商品价格
     */
    @ApiModelProperty(value = "商品价格")
    @TableField(value = "goods_price", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal goodsPrice;

}