package com.wqy.modules.shopping.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.wqy.modules.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author licm
 * @since 2020-04-01
 */
@ApiModel(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "sh_user")
public class ShUser extends Model<ShUser> implements Serializable {

    @ApiModelProperty("主键")
    @TableId(value = "id",type = IdType.INPUT)
    private String id;
    @ApiModelProperty("")
    @TableField(value = "username", fill = FieldFill.INSERT_UPDATE)
    private String username;
    @ApiModelProperty("")
    @TableField(value = "password", fill = FieldFill.INSERT_UPDATE)
    private String password;

    //手机号
    @TableField(value = "mobile", fill = FieldFill.INSERT_UPDATE)
    private String mobile;

    //邮箱
    @TableField(value = "email", fill = FieldFill.INSERT_UPDATE)
    private String email;

    //微信用户唯一标识
    @TableField(value = "openId", fill = FieldFill.INSERT_UPDATE)
    private String openId;

    @TableField(value = "is_active", fill = FieldFill.INSERT_UPDATE)
    private Integer isActive;
    @ApiModelProperty("")
    @TableField(value = "role_id", fill = FieldFill.INSERT_UPDATE)
    private String roleId;
    @ApiModelProperty("")
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Long createTime;
    @ApiModelProperty("")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}