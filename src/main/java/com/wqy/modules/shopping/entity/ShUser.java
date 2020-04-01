package com.wqy.modules.shopping.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import com.wqy.modules.common.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;

/**
* @author licm
* @since 2020-04-01
*/
@ApiModel(value ="")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sh_user")
public class ShUser  extends BaseEntity implements Serializable {

                @ApiModelProperty("主键")
                @Id
        private String id;
        @ApiModelProperty("")
        @TableField(value = "username", fill = FieldFill.INSERT_UPDATE)
private String username;
        @ApiModelProperty("")
        @TableField(value = "password", fill = FieldFill.INSERT_UPDATE)
private String password;

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
        @ApiModelProperty("")
        @TableField(value = "u_u", fill = FieldFill.INSERT_UPDATE)
private String uU;//关连表 用于分销
@Override
protected Serializable pkVal() {
        return this.id;
        }
}