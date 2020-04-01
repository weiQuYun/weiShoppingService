package com.zhengqing.modules.system.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Sh_User")
public class ShUser {
    @Id
    private Integer UserId;
    private String userName;
    private String passWord;
    private Byte isActive;
    private Integer roleId;
    private Long createTime;
    private Long updateTime;

    public ShUser() {
    }

    public ShUser(Integer userId, String userName, String passWord, Byte isActive, Integer roleId, Long createTime, Long updateTime) {
        UserId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.isActive = isActive;
        this.roleId = roleId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
