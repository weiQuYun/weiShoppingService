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
@ApiModel(value = "用户表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShUser  implements Serializable {

    /**
     * 唯一IDUUID
     */
    @ApiModelProperty(value = "唯一IDUUID")
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @TableField(value = "username", fill = FieldFill.INSERT_UPDATE)
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField(value = "password", fill = FieldFill.INSERT_UPDATE)
    private String password;
    /**
     * 是否可使用封号
     */
    @ApiModelProperty(value = "是否可使用封号")
    @TableField(value = "is_active", fill = FieldFill.INSERT_UPDATE)
    private Integer isActive;
    /**
     * 权限id 字典
     */
    @ApiModelProperty(value = "权限id 字典")
    @TableField(value = "role_id", fill = FieldFill.INSERT_UPDATE)
    private String roleId;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField(value = "mobile", fill = FieldFill.INSERT_UPDATE)
    private String mobile;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField(value = "email", fill = FieldFill.INSERT_UPDATE)
    private String email;
    /**
     * 前端发送唯一ID
     */
    @ApiModelProperty(value = "前端发送唯一ID ")
    @TableField(value = "openId", fill = FieldFill.INSERT_UPDATE)
    private String openId;
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}