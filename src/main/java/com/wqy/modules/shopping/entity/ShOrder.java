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
import java.util.Date;

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
    //订单id
    private String orderId;
    @ApiModelProperty("")
    @TableField(value = "receiver", fill = FieldFill.INSERT_UPDATE)
    //收款员
    private String receiver;
    @ApiModelProperty("")
    @TableField(value = "address", fill = FieldFill.INSERT_UPDATE)
    //地址
    private String address;
    @ApiModelProperty("")
    @TableField(value = "tel", fill = FieldFill.INSERT_UPDATE)
    private String tel;
    @TableField(value = "zcode", fill = FieldFill.INSERT_UPDATE)
    //邮政编码
    private String zcode;
    @ApiModelProperty("")
    @TableField(value = "total_price", fill = FieldFill.INSERT_UPDATE)
    //总金额
    private BigDecimal totalPrice;
    @ApiModelProperty("")
    @TableField(value = "member_id", fill = FieldFill.INSERT_UPDATE)
    //成员Id
    private String memberId;

    @TableField(value = "pay_status", fill = FieldFill.INSERT_UPDATE)
    //支付状态
    private Integer payStatus;

    @TableField(value = "send_status", fill = FieldFill.INSERT_UPDATE)
    //邮寄状态
    private Integer sendStatus;
    //支付宝支付成功返回的订单号
    @TableField(value = "ali_order_id", fill = FieldFill.INSERT_UPDATE)
    private String aliOrderId;
    @ApiModelProperty("")
    @TableField(value = "company", fill = FieldFill.INSERT_UPDATE)
    //物流公司
    private String company;

    @TableField(value = "number", fill = FieldFill.INSERT_UPDATE)
    //物流运单号
    private String number;
    @ApiModelProperty("")
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    @ApiModelProperty("")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}