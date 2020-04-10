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
import java.util.Date;

/**
 * @author licm
 * @since 2020-04-10
 */
@ApiModel(value = "字典表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShDict extends BaseEntity<ShDict> implements Serializable {

    /**
     * 主键keyId
     */
    @ApiModelProperty(value = "主键keyId")
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Integer id;
    /**
     * 父Id
     */
    @ApiModelProperty(value = "父Id")
    @TableField(value = "pid", fill = FieldFill.INSERT_UPDATE)
    private Long pid;
    /**
     * 字典类型名称
     */
    @ApiModelProperty(value = "字典类型名称")
    @TableField(value = "dict_type_name", fill = FieldFill.INSERT_UPDATE)
    private String dictTypeName;
    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    @TableField(value = "dict_type_code", fill = FieldFill.INSERT_UPDATE)
    private String dictTypeCode;
    @TableField(value = "dict_id", fill = FieldFill.INSERT_UPDATE)
    private String dictId;
    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    @TableField(value = "dict_name", fill = FieldFill.INSERT_UPDATE)
    private String dictName;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    @TableField(value = "sort_no", fill = FieldFill.INSERT_UPDATE)
    private Integer sortNo;
    @TableField(value = "level", fill = FieldFill.INSERT_UPDATE)
    private Integer level;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @TableField(value = "memo", fill = FieldFill.INSERT_UPDATE)
    private String memo;
    @TableField(value = "add_time", fill = FieldFill.INSERT)
    private Date addTime;
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

}