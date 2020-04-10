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
@ApiModel(value = "类别表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShCategory  implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private String id;
    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    @TableField(value = "cat_name", fill = FieldFill.INSERT_UPDATE)
    private String catName;
    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    @TableField(value = "pid", fill = FieldFill.INSERT_UPDATE)
    private String pid;
    /**
     * 1-显示 0-不显示
     */
    @ApiModelProperty(value = "1-显示 0-不显示")
    @TableField(value = "is_show", fill = FieldFill.INSERT_UPDATE)
    private Integer isShow;
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}