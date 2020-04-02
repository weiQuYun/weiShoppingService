package com.wqy.modules.shopping.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author licm
 * @since 2020-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "sh_goods")
public class ShGoods {

    @ApiModelProperty("主键")
    @Id
    private String id;
    @ApiModelProperty("")
    @TableField(value = "goods_name", fill = FieldFill.INSERT_UPDATE)
    private String goodsName;
    @ApiModelProperty("")
    @TableField(value = "goods_sn", fill = FieldFill.INSERT_UPDATE)
    private String goodsSn;
    // @TableField(value = "goods_price", fill = FieldFill.INSERT_UPDATE)
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
}