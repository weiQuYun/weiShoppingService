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
import java.math.BigDecimal;

/**
 * @author licm
 * @since 2020-04-01
 */
@ApiModel(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sh_order")
public class ShOrder extends BaseEntity implements Serializable {

    @ApiModelProperty("主键")
    @Id
    private String id;
    @ApiModelProperty("")
    @TableField(value = "order_id", fill = FieldFill.INSERT_UPDATE)
    private String orderId;
    @ApiModelProperty("")
    @TableField(value = "receiver", fill = FieldFill.INSERT_UPDATE)
    private String receiver;
    @ApiModelProperty("")
    @TableField(value = "address", fill = FieldFill.INSERT_UPDATE)
    private String address;
    @ApiModelProperty("")
    @TableField(value = "tel", fill = FieldFill.INSERT_UPDATE)
    private String tel;
    @TableField(value = "zcode", fill = FieldFill.INSERT_UPDATE)
    private String zcode;
    @ApiModelProperty("")
    @TableField(value = "total_price", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal totalPrice;
    @ApiModelProperty("")
    @TableField(value = "member_id", fill = FieldFill.INSERT_UPDATE)
    private String memberId;

    @TableField(value = "pay_status", fill = FieldFill.INSERT_UPDATE)
    private Integer payStatus;

    @TableField(value = "send_status", fill = FieldFill.INSERT_UPDATE)
    private Integer sendStatus;

    @TableField(value = "ali_order_id", fill = FieldFill.INSERT_UPDATE)
    private String aliOrderId;
    @ApiModelProperty("")
    @TableField(value = "order_time", fill = FieldFill.INSERT_UPDATE)
    private Integer orderTime;
    @ApiModelProperty("")
    @TableField(value = "company", fill = FieldFill.INSERT_UPDATE)
    private String company;

    @TableField(value = "number", fill = FieldFill.INSERT_UPDATE)
    private String number;
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