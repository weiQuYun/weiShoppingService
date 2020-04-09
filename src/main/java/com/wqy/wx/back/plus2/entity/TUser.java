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
@ApiModel(value = "后台用户")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUser extends BaseEntity<TUser> implements Serializable {

    /**
     * 用户登陆名
     */
    @ApiModelProperty(value = "用户登陆名")
    @TableField(value = "user_name", fill = FieldFill.INSERT_UPDATE)
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField(value = "password", fill = FieldFill.INSERT_UPDATE)
    private String password;
    /**
     * 角色表Id
     */
    @ApiModelProperty(value = "角色表Id")
    @TableField(value = "role_id", fill = FieldFill.INSERT_UPDATE)
    private String roleId;
    /**
     * 是否禁用此用户-101
     */
    @ApiModelProperty(value = "是否禁用此用户-101")
    @TableField(value = "is_active", fill = FieldFill.INSERT_UPDATE)
    private Boolean active;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @TableField(value = "latitude", fill = FieldFill.INSERT_UPDATE)
    private Integer latitude;
    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    @TableField(value = "longitude", fill = FieldFill.INSERT_UPDATE)
    private Integer longitude;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    @TableField(value = "address", fill = FieldFill.INSERT_UPDATE)
    private String address;


}