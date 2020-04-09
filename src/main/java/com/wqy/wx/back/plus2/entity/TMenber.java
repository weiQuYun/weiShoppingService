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
@ApiModel(value = "会员表i")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TMenber extends BaseEntity<TMenber> implements Serializable {

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @TableField(value = "user_name", fill = FieldFill.INSERT_UPDATE)
    private String userName;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField(value = "phone_number", fill = FieldFill.INSERT_UPDATE)
    private String phoneNumber;
    /**
     * 微信过来唯一字段
     */
    @ApiModelProperty(value = "微信过来唯一字段")
    @TableField(value = "open_id", fill = FieldFill.INSERT_UPDATE)
    private String openId;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    @TableField(value = "auth_id", fill = FieldFill.INSERT_UPDATE)
    private Integer authId;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    @TableField(value = "address", fill = FieldFill.INSERT_UPDATE)
    private String address;

}