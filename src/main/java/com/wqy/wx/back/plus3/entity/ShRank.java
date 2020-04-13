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
 * @since 2020-04-10
 */
@ApiModel(value = "团队分值排行榜")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShRank extends BaseEntity<ShRank> implements Serializable {

    /**
     * 自动生成id
     */
    @ApiModelProperty(value = "自动生成id")
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Integer id;
    /**
     * 队长id
     */
    @ApiModelProperty(value = "队长id")
    @TableField(value = "member_id", fill = FieldFill.INSERT_UPDATE)
    private String memberId;
    /**
     * 团队名
     */
    @ApiModelProperty(value = "团队名")
    @TableField(value = "captain_name", fill = FieldFill.INSERT_UPDATE)
    private String captainName;
    /**
     * 成绩分数
     */
    @ApiModelProperty(value = "成绩分数")
    @TableField(value = "grade", fill = FieldFill.INSERT_UPDATE)
    private Long grade;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}