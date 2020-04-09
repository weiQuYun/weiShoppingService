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
@ApiModel(value = "购物车")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TCart extends BaseEntity<TCart> implements Serializable {

    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺id")
    @TableField(value = "hotel_id", fill = FieldFill.INSERT_UPDATE)
    private String hotelId;
    /**
     * 会员id
     */
    @ApiModelProperty(value = "会员id")
    @TableField(value = "menber_id", fill = FieldFill.INSERT_UPDATE)
    private String menberId;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField(value = "procute_id", fill = FieldFill.INSERT_UPDATE)
    private String procuteId;
    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    @TableField(value = "procute_number", fill = FieldFill.INSERT_UPDATE)
    private Integer procuteNumber;

//    /**
//     * 商品表 减少ajax发送 查询购物车 默认查询购物车商品详情一起返回
//     * **/
//    @TableField(exist = false)
//    private List<TProduct> tProductList;

}