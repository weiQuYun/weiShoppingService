package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * @since 2020-04-09
 */
@ApiModel(value = "会员表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShMember extends BaseEntity<ShMember> implements Serializable {

    /**
     * 主键唯一标识符UUID
     */
    @ApiModelProperty(value = "主键唯一标识符UUID")
    @TableId(value = "id")
    @TableField(value = "id", fill = FieldFill.INSERT)
    private String id;
    /**
     * 父级ID UUID
     */
    @ApiModelProperty(value = "父级ID UUID")
    @TableField(value = "parent_id", fill = FieldFill.DEFAULT)
    private String parentId;
    /**
     * 用户名微信昵称
     */
    @ApiModelProperty(value = "用户名微信昵称")
    @TableField(value = "username", fill = FieldFill.DEFAULT)
    private String username;
    /**
     * 密码估计是随机的
     */
    @ApiModelProperty(value = "密码估计是随机的")
    @TableField(value = "password", fill = FieldFill.DEFAULT)
    private String password;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField(value = "email", fill = FieldFill.DEFAULT)
    private String email;
    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户电话")
    @TableField(value = "phone", fill = FieldFill.DEFAULT)
    private String phone;
    /**
     * qq登录的openid
     */
    @ApiModelProperty(value = "qq登录的openid")
    @TableField(value = "openid", fill = FieldFill.DEFAULT)
    private String openid;
    /**
     * 积分
     */
    @ApiModelProperty(value = "积分")
    @TableField(value = "integral", fill = FieldFill.DEFAULT)
    private Long integral;
    /**
     * 积分改变总数
     */
    @ApiModelProperty(value = "积分改变总数")
    @TableField(value = "integral_change_count", fill = FieldFill.DEFAULT)
    private Integer integralChangeCount;
    /**
     * 积分转换率
     */
    @ApiModelProperty(value = "积分转换率")
    @TableField(value = "integral_change_rate", fill = FieldFill.DEFAULT)
    private Integer integralChangeRate;
    /**
     * 是否是团长
     */
    @ApiModelProperty(value = "是否是团长")
    @TableField(value = "ifs_leader", fill = FieldFill.DEFAULT)
    private Boolean ifsLeader;

    /**
     * 下级分销可用数
     */
    @ApiModelProperty(value = "下级分销可用数")
    @TableField(value = "share_number", fill = FieldFill.DEFAULT)
    private Integer shareNumber;
    /**
     * vip等级字段查字典
     */
    @ApiModelProperty(value = "vip等级字段查字典")
    @TableField(value = "lv_vip", fill = FieldFill.DEFAULT)
    private Integer lvVip;
    /**
     * 是否队长 0 否 1是
     */
    @ApiModelProperty(value = "是否队长 0 否 1是")
    @TableField(value = "ifs_captain", fill = FieldFill.DEFAULT)
    private Integer ifsCaptain;
    /**
     * 团队名称
     */
    @ApiModelProperty(value = "团队名称")
    @TableField(value = "captain_name", fill = FieldFill.DEFAULT)
    private String captainName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 跟新时间
     */
    @ApiModelProperty(value = "跟新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
