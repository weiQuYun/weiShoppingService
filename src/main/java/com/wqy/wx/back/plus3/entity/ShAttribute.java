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
import java.time.LocalDateTime;

/**
 * @author licm
 * @since 2020-04-09
 */
@ApiModel(value = "属性表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShAttribute  implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private String id;
    /**
     * 商品类型ID
     */
    @ApiModelProperty(value = "商品类型ID")
    @TableField(value = "type_id", fill = FieldFill.INSERT_UPDATE)
    private String typeId;
    /**
     * 属性名称
     */
    @ApiModelProperty(value = "属性名称")
    @TableField(value = "attr_name", fill = FieldFill.INSERT_UPDATE)
    private String attrName;
    /**
     * 属性类型 （0 唯一属性，1单选属性）
     */
    @ApiModelProperty(value = "属性类型 （0 唯一属性，1单选属性）")
    @TableField(value = "attr_type", fill = FieldFill.INSERT_UPDATE)
    private Integer attrType;
    /**
     * 属性录入方式 0 手动 1列表选择
     */
    @ApiModelProperty(value = "属性录入方式 0 手动 1列表选择")
    @TableField(value = "attr_input_type", fill = FieldFill.INSERT_UPDATE)
    private Integer attrInputType;
    /**
     * 属性值 多个用|分割
     */
    @ApiModelProperty(value = "属性值 多个用|分割")
    @TableField(value = "attr_values", fill = FieldFill.INSERT_UPDATE)
    private String attrValues;
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