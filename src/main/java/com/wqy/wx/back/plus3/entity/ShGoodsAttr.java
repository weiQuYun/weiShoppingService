package com.wqy.wx.back.plus3.entity;

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
import java.time.LocalDateTime;

/**
 * @author licm
 * @since 2020-04-09
 */
@ApiModel(value = "商品详情表用于商品页面的商品详情")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShGoodsAttr  implements Serializable {

    /**
     * UUID
     */
    @ApiModelProperty(value = "UUID")
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private String id;
    /**
     * 商品Id
     */
    @ApiModelProperty(value = "商品Id")
    @TableField(value = "goods_id", fill = FieldFill.INSERT_UPDATE)
    private String goodsId;
    /**
     * 属性主键
     */
    @ApiModelProperty(value = "属性主键")
    @TableField(value = "attr_id", fill = FieldFill.INSERT_UPDATE)
    private String attrId;
    /**
     * 属性值
     */
    @ApiModelProperty(value = "属性值")
    @TableField(value = "attr_value", fill = FieldFill.INSERT_UPDATE)
    private String attrValue;
    /**
     * 属性价格
     */
    @ApiModelProperty(value = "属性价格")
    @TableField(value = "attr_price", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal attrPrice;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}