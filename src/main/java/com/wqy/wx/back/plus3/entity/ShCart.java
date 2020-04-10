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
import java.time.LocalDateTime;

/**
 * @author licm
 * @since 2020-04-09
 */
@ApiModel(value = "购物车表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShCart extends BaseEntity<ShCart> implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private Long id;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField(value = "goods_id", fill = FieldFill.INSERT_UPDATE)
    private String goodsId;
    /**
     * 属性id
     */
    @ApiModelProperty(value = "属性id")
    @TableField(value = "goods_attr_ids", fill = FieldFill.INSERT_UPDATE)
    private String goodsAttrIds;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @TableField(value = "goods_number", fill = FieldFill.INSERT_UPDATE)
    private Integer goodsNumber;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableField(value = "user_id", fill = FieldFill.INSERT_UPDATE)
    private String userId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}