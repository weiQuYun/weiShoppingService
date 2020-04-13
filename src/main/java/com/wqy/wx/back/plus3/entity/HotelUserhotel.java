package com.wqy.wx.back.plus3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wqy.wx.back.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
* @author licm
* @since 2020-04-13
*/
@ApiModel(value ="线下店铺表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HotelUserhotel  extends BaseEntity<HotelUserhotel> implements Serializable {

    /**
    * 自增ID 无意义
    */
        @ApiModelProperty(value = "自增ID 无意义")
            @TableId(value = "id", type = IdType.AUTO)
                @TableField(value = "id", fill = FieldFill.INSERT_UPDATE)
private Integer id;
    /**
    * 店铺idUUID
    */
        @ApiModelProperty(value = "店铺idUUID")
        @TableField(value = "hotel_id", fill = FieldFill.INSERT_UPDATE)
private String hotelId;
    /**
    * 地址
    */
        @ApiModelProperty(value = "地址")
        @TableField(value = "address", fill = FieldFill.INSERT_UPDATE)
private String address;
    /**
    * 电话
    */
        @ApiModelProperty(value = "电话")
        @TableField(value = "tell", fill = FieldFill.INSERT_UPDATE)
private String tell;
    /**
    * userID
    */
        @ApiModelProperty(value = "userID")
        @TableField(value = "user_id", fill = FieldFill.INSERT_UPDATE)
private String userId;

}