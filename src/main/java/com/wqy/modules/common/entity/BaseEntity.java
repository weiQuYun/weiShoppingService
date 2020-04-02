package com.wqy.modules.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p> 修改时间 </p>
 *
 * @description:
 * @author: wqy
 * @date: 2019/8/18 0018 1:30
 */
@Data
public abstract class BaseEntity<T extends Model> extends BaseAddEntity<T> {

}
