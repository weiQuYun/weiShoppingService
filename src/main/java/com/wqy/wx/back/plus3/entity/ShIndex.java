package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "首页公告表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShIndex  implements Serializable {

    /**
     * 主键keyId
     */
    @ApiModelProperty(value = "主键Id")
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private int id;
    /**
     *
     * **/
    @ApiModelProperty(value = "首页公告详情")
    @TableField(value = "text", fill = FieldFill.INSERT_UPDATE)
    private String text;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    /**
     * 更新时间
     * **/
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}