package com.wqy.modules.shopping.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.wqy.modules.common.entity.BaseEntity;
import lombok.Cleanup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "sh_member_sub")
@Data
public class ShMemberSub extends BaseEntity implements Serializable {

    @Id
    private String id;

    @TableField(value = "user_id", fill = FieldFill.INSERT_UPDATE)
    private ShMember shUser;

    @TableField(value = "user_id", fill = FieldFill.INSERT_UPDATE)
    private ShMember shUserSub;

    //是否选择代理
    @TableField(value = "user_id", fill = FieldFill.INSERT_UPDATE)
    private Boolean isAgent;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
