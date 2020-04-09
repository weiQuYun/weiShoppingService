package com.wqy.wx.back.plus2.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author licm
 * @since 2020-04-03
 */
@ApiModel(value = "订单页面")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TOrder implements Serializable {

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @TableField(value = "order_number", fill = FieldFill.INSERT_UPDATE)
    private String orderNumber = "";
    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    @TableField(value = "receiver", fill = FieldFill.INSERT_UPDATE)
    private String receiver = "";
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField(value = "phone", fill = FieldFill.INSERT_UPDATE)
    private String phone = "";
    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "邮政编码")
    @TableField(value = "zcode", fill = FieldFill.INSERT_UPDATE)
    private String zcode = "";
    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    @TableField(value = "menber_id", fill = FieldFill.INSERT_UPDATE)
    private String menberId = "0";
    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态")
    @TableField(value = "pay_status", fill = FieldFill.INSERT_UPDATE)
    private Integer payStatus = 0;
    /**
     * 快递状态
     */
    @ApiModelProperty(value = "快递状态")
    @TableField(value = "send_status", fill = FieldFill.INSERT_UPDATE)
    private Integer sendStatus = 0;
    /**
     * 支付成功返回单号
     */
    @ApiModelProperty(value = "支付成功返回单号")
    @TableField(value = "ali_order_id", fill = FieldFill.INSERT_UPDATE)
    private Long aliOrderId = 0l;

    /**
     * 物流公司
     */
    @ApiModelProperty(value = "物流公司")
    @TableField(value = "company", fill = FieldFill.INSERT_UPDATE)
    private String company = "";
    /**
     * 物流单号
     */
    @ApiModelProperty(value = "物流单号")
    @TableField(value = "number", fill = FieldFill.INSERT_UPDATE)
    private String number = "";
    /**
     * 留言字段
     **/
    @ApiModelProperty(value = "留言字段")
    @TableField(value = "message", fill = FieldFill.INSERT_UPDATE)
    private String message = "";

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    /**
     * 创建日期 - 现在时表示主动创建
     */
    @ApiModelProperty(value = "创建日期", example = "1900/01/01 00:00:00")
    private Date createTime = new Date();

}