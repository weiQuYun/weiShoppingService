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
import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@ApiModel(value = "商品分类表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TProductCates extends BaseEntity<TProductCates> implements Serializable {

    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    @TableField(value = "cates_name", fill = FieldFill.INSERT_UPDATE)
    private String catesName;
    /**
     * 分类状态
     */
    @ApiModelProperty(value = "分类状态")
    @TableField(value = "status", fill = FieldFill.INSERT_UPDATE)
    private Integer status;
    /**
     * 分类组
     */
    @ApiModelProperty(value = "分类组")
    @TableField(value = "class_id", fill = FieldFill.INSERT_UPDATE)
    private Integer classId;

    /**
     * 商品集
     **/
    @TableField(exist = false)
    private List<TProduct> tProductList;

}