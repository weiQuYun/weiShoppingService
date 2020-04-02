package com.wqy.modules.shopping.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.wqy.modules.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author licm
 * @since 2020-04-01
 */
@ApiModel(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sh_order_goods")
public class ShOrderGoods extends BaseEntity implements Serializable {

    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("")
    @TableField(value = "order_id", fill = FieldFill.INSERT_UPDATE)
    private String orderId;
    @ApiModelProperty("")
    @TableField(value = "goods_id", fill = FieldFill.INSERT_UPDATE)
    private String goodsId;
    @ApiModelProperty("")
    @TableField(value = "goods_attr_ids", fill = FieldFill.INSERT_UPDATE)
    private String goodsAttrIds;
    @ApiModelProperty("")
    @TableField(value = "goods_number", fill = FieldFill.INSERT_UPDATE)
    private Integer goodsNumber;
    @ApiModelProperty("")
    @TableField(value = "goods_price", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal goodsPrice;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}