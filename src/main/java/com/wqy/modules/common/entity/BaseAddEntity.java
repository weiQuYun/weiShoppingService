package com.wqy.modules.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * <p> 创建日期 </p>
 *
 * @description:
 * @author: wqy
 * @date: 2019/8/18 0018 1:34
 */
@Data
public abstract class BaseAddEntity<T extends Model> extends Model<T> {

}
