package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
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
/**
* @author licm
* @since 2020-04-11
*/
@ApiModel(value ="钱包")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShMoney  extends BaseEntity<ShMoney> implements Serializable {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private String id;
    /**
     * 金额/积分  1:10
     */
    @ApiModelProperty(value = "金额/积分  1:10")
    @TableField(value = "amount", fill = FieldFill.INSERT_UPDATE)
    private BigDecimal amount;

}