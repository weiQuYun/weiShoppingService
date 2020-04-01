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
@TableName("sh_member")
public class ShMember  extends BaseEntity implements Serializable {

                @ApiModelProperty("主键")
                @Id
        private String id;
        @ApiModelProperty("")
        @TableField(value = "username", fill = FieldFill.INSERT_UPDATE)
private String username;
        @ApiModelProperty("")
        @TableField(value = "password", fill = FieldFill.INSERT_UPDATE)
private String password;
        @ApiModelProperty("")
        @TableField(value = "email", fill = FieldFill.INSERT_UPDATE)
private String email;
        @ApiModelProperty("")
        @TableField(value = "phone", fill = FieldFill.INSERT_UPDATE)
private String phone;
        @ApiModelProperty("")
        @TableField(value = "openid", fill = FieldFill.INSERT_UPDATE)
private String openid;
        @ApiModelProperty("")
        @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
private Integer createTime;
        @ApiModelProperty("")
        @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
private Integer updateTime;
@Override
protected Serializable pkVal() {
        return this.id;
        }
}