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
@ApiModel(value = "签到表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShUserSign extends BaseEntity<ShUserSign> implements Serializable {

    /**
     * 自增无意义id
     */
    @ApiModelProperty(value = "自增无意义id")
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Integer id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableField(value = "user_id", fill = FieldFill.INSERT_UPDATE)
    private String userId;
    /**
     * 签到时间
     */
    @ApiModelProperty(value = "签到时间")
    @TableField(value = "sign_in", fill = FieldFill.INSERT)
    private Date signIn;

}