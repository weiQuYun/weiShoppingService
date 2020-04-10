package com.wqy.wx.back.plus3.entity;

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
 * @since 2020-04-09
 */
@ApiModel(value = "是否是代理商表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShMemberSub  implements Serializable {

    /**
     * UUID唯一标识符
     */
    @ApiModelProperty(value = "UUID唯一标识符")
    @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
    private String id;
    /**
     * user的id
     */
    @ApiModelProperty(value = "user的id")
    @TableField(value = "user_id", fill = FieldFill.INSERT_UPDATE)
    private String userId;
    /**
     * 是否是代理商
     */
    @ApiModelProperty(value = "是否是代理商")
    @TableField(value = "is_agent", fill = FieldFill.INSERT_UPDATE)
    private Integer isAgent;

}