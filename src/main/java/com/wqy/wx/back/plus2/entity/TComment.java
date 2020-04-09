package com.wqy.wx.back.plus2.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wqy.wx.back.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author licm
 * @since 2020-04-03
 */
@ApiModel(value = "评论投诉表i")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TComment extends BaseEntity<TComment> implements Serializable {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableField(value = "user_id", fill = FieldFill.INSERT_UPDATE)
    private String userId;
    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    @TableField(value = "menber_id", fill = FieldFill.INSERT_UPDATE)
    private String menberId;
    /**
     * 产品ID
     */
    @ApiModelProperty(value = "产品ID")
    @TableField(value = "product_id", fill = FieldFill.INSERT_UPDATE)
    private String productId;
    /**
     * 评论
     */
    @ApiModelProperty(value = "评论")
    @TableField(value = "comment", fill = FieldFill.INSERT_UPDATE)
    private String comment;
    /**
     * 评分
     */
    @ApiModelProperty(value = "评分")
    @TableField(value = "fraction", fill = FieldFill.INSERT_UPDATE)
    private Integer fraction;

}