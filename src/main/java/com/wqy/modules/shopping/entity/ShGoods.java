package com.wqy.modules.shopping.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import com.wqy.modules.common.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;

/**
 * @author licm
 * @since 2020-04-01
 */
@ApiModel(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sh_goods")
public class ShGoods extends BaseEntity implements Serializable {

    @ApiModelProperty("主键")
    @Id
    private String id;
    @ApiModelProperty("")
    @TableField(value = "goods_name", fill = FieldFill.INSERT_UPDATE)
    private String goodsName;
    @ApiModelProperty("")
    @TableField(value = "goods_sn", fill = FieldFill.INSERT_UPDATE)
    private String goodsSn;
    @TableField(value = "goods_price", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal goodsPrice;
    @ApiModelProperty("")
    @TableField(value = "goods_number", fill = FieldFill.INSERT_UPDATE)
    private Integer goodsNumber;
    @ApiModelProperty("")
    @TableField(value = "type_id", fill = FieldFill.INSERT_UPDATE)
    private String typeId;
    @ApiModelProperty("")
    @TableField(value = "cat_id", fill = FieldFill.INSERT_UPDATE)
    private String catId;
    @ApiModelProperty("")
    @TableField(value = "goods_img", fill = FieldFill.INSERT_UPDATE)
    private String goodsImg;
    @ApiModelProperty("")
    @TableField(value = "goods_thumb", fill = FieldFill.INSERT_UPDATE)
    private String goodsThumb;
    @ApiModelProperty("")
    @TableField(value = "add_time", fill = FieldFill.INSERT_UPDATE)
    private Integer addTime;

    @TableField(value = "is_delete", fill = FieldFill.INSERT_UPDATE)
    private Integer isDelete;
    @TableField(value = "is_sale", fill = FieldFill.INSERT_UPDATE)
    private Integer isSale;

    @TableField(value = "is_new", fill = FieldFill.INSERT_UPDATE)
    private Integer isNew;

    @TableField(value = "is_best", fill = FieldFill.INSERT_UPDATE)
    private Integer isBest;


    @TableField(value = "is_hot", fill = FieldFill.INSERT_UPDATE)
    private Integer isHot;
    @ApiModelProperty("")
    @TableField(value = "goods_desc", fill = FieldFill.INSERT_UPDATE)
    private String goodsDesc;
    @ApiModelProperty("")
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Integer createTime;
    @ApiModelProperty("")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Integer updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}