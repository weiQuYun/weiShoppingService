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
import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@ApiModel(value = "商品表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TProduct extends BaseEntity<TProduct> implements Serializable {

    /**
     * 商品名
     */
    @ApiModelProperty(value = "商品名")
    @TableField(value = "pro_name", fill = FieldFill.INSERT_UPDATE)
    private String proName;
    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字")
    @TableField(value = "key_words", fill = FieldFill.INSERT_UPDATE)
    private String keyWords;
    /**
     * 图片标题URL
     */
    @ApiModelProperty(value = "图片标题URL")
    @TableField(value = "pro_image", fill = FieldFill.INSERT_UPDATE)
    private String proImage;

    /**
     * 原价
     */
    @ApiModelProperty(value = "原价")
    @TableField(value = "price_old", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal priceOld;
    /**
     * 售价
     */
    @ApiModelProperty(value = "售价")
    @TableField(value = "price_new", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal priceNew;
    /**
     * 简单描述
     */
    @ApiModelProperty(value = "简单描述")
    @TableField(value = "memo", fill = FieldFill.INSERT_UPDATE)
    private String memo;
    /**
     * 点击数
     */
    @ApiModelProperty(value = "点击数")
    @TableField(value = "click", fill = FieldFill.INSERT_UPDATE)
    private Integer click;
    /**
     * 上架状态-101
     */
    @ApiModelProperty(value = "上架状态-101")
    @TableField(value = "status", fill = FieldFill.INSERT_UPDATE)
    private Boolean status;
    /**
     * 商品分类ID
     */
    @ApiModelProperty(value = "商品分类ID")
    @TableField(value = "cid", fill = FieldFill.INSERT_UPDATE)
    private String cid;
    /**
     * 库存
     */
    @ApiModelProperty(value = "库存")
    @TableField(value = "pro_number", fill = FieldFill.INSERT_UPDATE)
    private Integer proNumber;
    /**
     * 已售
     */
    @ApiModelProperty(value = "已售")
    @TableField(value = "pro_out", fill = FieldFill.INSERT_UPDATE)
    private Integer proOut;

    /**
     * 图片URL集
     **/
    @TableField(exist = false)
    private List<TProductImage> product_image = null;

    /**
     * 商品详情页
     **/
    @TableField(exist = false)
    private TProductContents tProductContents = null;

    /**
     * 店铺ID
     **/
    @ApiModelProperty(value = "店铺ID")
    @TableField(value = "hotel_id", fill = FieldFill.INSERT_UPDATE)
    private String hotelId;

}