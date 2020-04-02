package com.wqy.modules.shopping.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.wqy.modules.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author licm
 * @since 2020-04-01
 */
@ApiModel(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sh_auth")
public class ShAuth extends BaseEntity implements Serializable {

    @ApiModelProperty("主键")
    @Id
    private String id;
    @ApiModelProperty("")
    @TableField(value = "auth_name", fill = FieldFill.INSERT_UPDATE)
    private String authName;
    @ApiModelProperty("")
    @TableField(value = "auth_c", fill = FieldFill.INSERT_UPDATE)
    private String authC;
    @ApiModelProperty("")
    @TableField(value = "auth_a", fill = FieldFill.INSERT_UPDATE)
    private String authA;
    @ApiModelProperty("")
    @TableField(value = "pid", fill = FieldFill.INSERT_UPDATE)
    private String pid;
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