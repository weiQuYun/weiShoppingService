package com.wqy.wx.back.plus2.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wqy.wx.back.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author licm
 * @since 2020-04-03
 */
@ApiModel(value = "角色权限")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TRole extends BaseEntity<TRole> implements Serializable {

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    @TableField(value = "role_name", fill = FieldFill.INSERT_UPDATE)
    private String roleName;
    /**
     * 权限字段1234567
     */
    @ApiModelProperty(value = "权限字段1234567")
    @TableField(value = "auth_role", fill = FieldFill.INSERT_UPDATE)
    private Boolean authRole;

}