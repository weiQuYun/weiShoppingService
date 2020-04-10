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
@ApiModel(value = "分类表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShType implements Serializable {

    /**
     * 分类ID,UUID
     */
    @ApiModelProperty(value = "分类ID,UUID")
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private String id;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    @TableField(value = "type_name", fill = FieldFill.INSERT_UPDATE)
    private String typeName;
    /**
     * 分类描述
     */
    @ApiModelProperty(value = "分类描述")
    @TableField(value = "mark", fill = FieldFill.INSERT_UPDATE)
    private String mark;
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