package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author licm
 * @since 2020-04-09
 */
@ApiModel(value = "商品表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShGoods implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id",type = IdType.INPUT)
    @TableField(value = "id")
    private String id;
    /**
     * 商品名
     */
    @ApiModelProperty(value = "商品名")
    @TableField(value = "goods_name", fill = FieldFill.DEFAULT)
    private String goodsName;
    /**
     * 系统生成唯一货号
     */
    @ApiModelProperty(value = "系统生成唯一货号")
    @TableField(value = "goods_sn", fill = FieldFill.DEFAULT)
    private String goodsSn;
    /**
     * 商品价格
     */
    @ApiModelProperty(value = "商品价格")
    @TableField(value = "goods_price", fill = FieldFill.DEFAULT)
    private BigDecimal goodsPrice;
    /**
     * 商品库存
     */
    @ApiModelProperty(value = "商品库存")
    @TableField(value = "goods_number", fill = FieldFill.DEFAULT)
    private Integer goodsNumber;
    /**
     * 所属类型
     */
    @ApiModelProperty(value = "所属类型")
    @TableField(value = "type_id", fill = FieldFill.DEFAULT)
    private String typeId;
    /**
     * 所属分类
     */
    @ApiModelProperty(value = "所属分类")
    @TableField(value = "cat_id", fill = FieldFill.DEFAULT)
    private String catId;
    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    @TableField(value = "goods_img", fill = FieldFill.DEFAULT)
    private String goodsImg;
    /**
     * 商品缩略图
     */
    @ApiModelProperty(value = "商品缩略图")
    @TableField(value = "goods_thumb", fill = FieldFill.DEFAULT)
    private String goodsThumb;
    /**
     * 是否上架 0 未上架 1已上架
     */
    @ApiModelProperty(value = "是否上架 0 未上架 1已上架")
    @TableField(value = "is_sale", fill = FieldFill.DEFAULT)
    private Integer isSale;
    /**
     * 是否新品
     */
    @ApiModelProperty(value = "是否新品")
    @TableField(value = "is_new", fill = FieldFill.DEFAULT)
    private Integer isNew;
    /**
     * 是否推荐
     */
    @ApiModelProperty(value = "是否推荐")
    @TableField(value = "is_best", fill = FieldFill.DEFAULT)
    private Integer isBest;
    /**
     * 是否热卖
     */
    @ApiModelProperty(value = "是否热卖")
    @TableField(value = "is_hot", fill = FieldFill.DEFAULT)
    private Integer isHot;
    /**
     * 商品描述
     */
    @ApiModelProperty(value = "商品描述")
    @TableField(value = "goods_desc", fill = FieldFill.DEFAULT)
    private String goodsDesc;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(exist = false)
    private List<ShGoodsAttr> goodsAttrList;
    @TableField(exist = false)
    private BigDecimal goodsIntegral;
    @TableField(exist = false)
    private BigDecimal goodsPriceNew;


}
