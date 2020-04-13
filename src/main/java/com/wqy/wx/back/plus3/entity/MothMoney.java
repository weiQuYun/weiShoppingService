package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wqy.wx.back.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "月钱表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MothMoney extends BaseEntity<ShAttribute> implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id")
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableField(value = "member_id", fill = FieldFill.INSERT_UPDATE)
    private String memberId;
    @ApiModelProperty(value = "money")
    @TableField(value = "money", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal money;
    @ApiModelProperty(value = "status")
    @TableField(value = "status", fill = FieldFill.INSERT_UPDATE)
    private Integer status;
    @ApiModelProperty(value = "create_time")
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
}