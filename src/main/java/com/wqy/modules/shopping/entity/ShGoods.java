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
import java.util.Date;

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
    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name", fill = FieldFill.INSERT_UPDATE)
    private String goodsName;
    @ApiModelProperty("商品货号")
    @TableField(value = "goods_sn", fill = FieldFill.INSERT_UPDATE)
    private String goodsSn;
    @ApiModelProperty("商品价格")
    @TableField(value = "goods_price", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal goodsPrice;
    @ApiModelProperty("商品库存")
    @TableField(value = "goods_number", fill = FieldFill.INSERT_UPDATE)
    private Integer goodsNumber;
    @ApiModelProperty("所属类型")
    @TableField(value = "type_id", fill = FieldFill.INSERT_UPDATE)
    private String typeId;
    @ApiModelProperty("所属分类")
    @TableField(value = "cat_id", fill = FieldFill.INSERT_UPDATE)
    private String catId;
    @ApiModelProperty("商品图片")
    @TableField(value = "goods_img", fill = FieldFill.INSERT_UPDATE)
    private String goodsImg;
    @ApiModelProperty("商品缩略图")
    @TableField(value = "goods_thumb", fill = FieldFill.INSERT_UPDATE)
    private String goodsThumb;
    //是否上架 0 未上架  1 上架
    @TableField(value = "is_sale", fill = FieldFill.INSERT_UPDATE)
    private Boolean isSale;

    //是否新品  0 不是  1 是
    @TableField(value = "is_new", fill = FieldFill.INSERT_UPDATE)
    private Boolean isNew;

    //是否推荐 0 不推荐  1 推荐
    @TableField(value = "is_best", fill = FieldFill.INSERT_UPDATE)
    private Boolean isBest;

    //是否热卖 0 不是热卖上架  1 是热卖上架
    @TableField(value = "is_hot", fill = FieldFill.INSERT_UPDATE)
    private Boolean isHot;
    @ApiModelProperty("商品详情")
    @TableField(value = "goods_desc", fill = FieldFill.INSERT_UPDATE)
    private String goodsDesc;
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date createTime=new Date();
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @ApiModelProperty("销量")
    @TableField(value = "sales",fill = FieldFill.INSERT_UPDATE)
    private Long sales;
}