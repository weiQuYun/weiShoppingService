package com.wqy.modules.shopping.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import com.wqy.modules.common.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;

/**
* @author licm
* @since 2020-04-01
*/
@ApiModel(value ="")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sh_attribute")
public class ShAttribute  extends BaseEntity implements Serializable {

                @ApiModelProperty("主键")
                @Id
        private String id;
        @TableField(value = "type_id", fill = FieldFill.INSERT_UPDATE)
private String typeId;
        @ApiModelProperty("")
        @TableField(value = "attr_name", fill = FieldFill.INSERT_UPDATE)
private String attrName;
        @TableField(value = "attr_type", fill = FieldFill.INSERT_UPDATE)
private Integer attrType;
        @TableField(value = "attr_input_type", fill = FieldFill.INSERT_UPDATE)
private Integer attrInputType;
private String attrValues;
        @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
private Integer createTime;
        @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
private Integer updateTime;
@Override
protected Serializable pkVal() {
        return this.id;
        }
}