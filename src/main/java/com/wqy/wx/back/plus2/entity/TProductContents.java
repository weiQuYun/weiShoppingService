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
@ApiModel(value = "商品详情介绍表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TProductContents extends BaseEntity<TProductContents> implements Serializable {

    /**
     * 详情描述
     */
    @ApiModelProperty(value = "详情描述")
    @TableField(value = "contents", fill = FieldFill.INSERT_UPDATE)
    private String contents;

}