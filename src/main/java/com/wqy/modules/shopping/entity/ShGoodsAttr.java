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

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author licm
 * @since 2020-04-01
 */
@ApiModel(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sh_goods_attr")
public class ShGoodsAttr extends BaseEntity implements Serializable {

    @ApiModelProperty("主键")
    @Id
    private String id;
    @ApiModelProperty("商品主键")
    //商品主键
    @TableField(value = "goods_id", fill = FieldFill.INSERT_UPDATE)
    private String goodsId;
    @ApiModelProperty("属性主键")
    @TableField(value = "attr_id", fill = FieldFill.INSERT_UPDATE)
    //属性主键
    private String attrId;
    @ApiModelProperty("属性详情")
    @TableField(value = "attr_value", fill = FieldFill.INSERT_UPDATE)
    //属性详情
    private String attrValue;
    @ApiModelProperty("价格")
    @TableField(value = "attr_price", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal attrPrice;
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}