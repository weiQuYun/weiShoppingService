package com.wqy.modules.shopping.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.wqy.modules.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author licm
 * @since 2020-04-01
 */
@ApiModel(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sh_member")
public class ShMember extends BaseEntity implements Serializable {

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

    //积分
    @TableField(value = "integral")
    private Double integral;

    //积分兑换次数
    @TableField(value = "integral_change_count")
    private int integralChangeCount;

    //积分兑换率
    @TableField(value = "integral_change_rate")
    private int integralChangeRate;

    //总分红
    @TableField(value = "count_receive")
   private BigDecimal countReceive;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}