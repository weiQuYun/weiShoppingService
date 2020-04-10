package com.wqy.wx.back.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: BaseEntity
 * @Description: TODO
 * @Author licm
 * @Date 2020/3/7 23:29
 * @Version V1.0
 * @Explain :
 **/
@Slf4j
@Data
public abstract class BaseEntity<T extends Model<?>> extends Model<T> implements Serializable {


}
