package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wqy.wx.back.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author licm
 * @since 2020-04-10
 */
@ApiModel(value = "首页公告表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShIndex extends BaseEntity<ShIndex> implements Serializable {

    /**
     * 首页ID无意义自增
     */
    @ApiModelProperty(value = "首页ID无意义自增")
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Integer id;
    /**
     * 首页公告详情
     */
    @ApiModelProperty(value = "首页公告详情")
    @TableField(value = "text", fill = FieldFill.INSERT_UPDATE)
    private String text;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}