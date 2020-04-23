package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author licm
 * @since 2020-04-09
 */
@ApiModel(value = "订单表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShOrder implements Serializable {

    /**
     * 订单id 自增无意义
     */
    @ApiModelProperty(value = "订单id 自增无意义")
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private Integer id;
    /**
     * 订单实际ID UUID
     */
    @ApiModelProperty(value = "订单实际ID UUID")
    @TableField(value = "order_id", fill = FieldFill.INSERT_UPDATE)
    private String orderId;
    /**
     * 收件人名字
     */
    @ApiModelProperty(value = "收件人名字")
    @TableField(value = "receiver", fill = FieldFill.INSERT_UPDATE)
    private String receiver;
    /**
     * 收件人地址
     */
    @ApiModelProperty(value = "收件人地址")
    @TableField(value = "address", fill = FieldFill.INSERT_UPDATE)
    private String address;
    /**
     * 收件人手机
     */
    @ApiModelProperty(value = "收件人手机")
    @TableField(value = "tel", fill = FieldFill.INSERT_UPDATE)
    private String tel;
    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "邮政编码")
    @TableField(value = "zcode", fill = FieldFill.INSERT_UPDATE)
    private String zcode;
    /**
     * 总价
     */
    @ApiModelProperty(value = "总价")
    @TableField(value = "total_price", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal totalPrice;
    /**
     * 会员id UUID
     */
    @ApiModelProperty(value = "会员id UUID")
    @TableField(value = "member_id", fill = FieldFill.INSERT_UPDATE)
    private String memberId;
    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态")
    @TableField(value = "pay_status", fill = FieldFill.INSERT_UPDATE)
    private Integer payStatus;
    /**
     * 发货状态
     */
    @ApiModelProperty(value = "发货状态")
    @TableField(value = "send_status", fill = FieldFill.INSERT_UPDATE)
    private Integer sendStatus;
    /**
     * 支付成功返回码
     */
    @ApiModelProperty(value = "支付成功返回码")
    @TableField(value = "ali_order_id", fill = FieldFill.INSERT_UPDATE)
    private String aliOrderId;
    /**
     * 快递公司名称
     */
    @ApiModelProperty(value = "快递公司名称")
    @TableField(value = "company", fill = FieldFill.DEFAULT)
    private String company;
    /**
     * 快递订单号
     */
    @ApiModelProperty(value = "快递订单号")
    @TableField(value = "number", fill = FieldFill.DEFAULT)
    private String number;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    @ApiModelProperty(value = "商品表")
    @TableField(exist = false)
    private ShGoods shGoods;
    @ApiModelProperty(value = "商品属性表")
    @TableField(exist = false)
    private ShGoodsAttr shGoodsAttr;

}
