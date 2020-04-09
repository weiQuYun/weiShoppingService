package com.wqy.wx.back.plus2.entity;

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
import java.util.Date;

/**
 * @author licm
 * @since 2020-04-03
 */
@ApiModel(value = "商品图片more")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TProductImage implements Serializable {

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField(value = "product_id", fill = FieldFill.INSERT_UPDATE)
    private String productId;
    /**
     * 图片表关联t_product
     */
    @ApiModelProperty(value = "图片表关联t_product")
    @TableField(value = "product_image", fill = FieldFill.INSERT_UPDATE)
    private String productImage;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id = 0;
    /**
     * 创建日期 - 现在时表示主动创建
     */
    @ApiModelProperty(value = "创建日期", example = "1900/01/01 00:00:00")
    private Date createTime = new Date();


}