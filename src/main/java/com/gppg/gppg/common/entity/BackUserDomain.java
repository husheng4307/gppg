package com.gppg.gppg.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author: Yang
 * date: 2020/9/3 16:43
 * des:
 */
@TableName("back_user")
public class BackUserDomain {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    /**
     * 账户
     */
    String account;
    /**
     * 密码
     */
    String password;
    /**
     * 盐
     */
    String salt;
    /**
     * 名称
     */
    String name;
    /**
     * 工号
     */
    @TableId(value = "work_id")
    String workId;
    /**
     * 学校id
     */
    @TableId(value = "school_id")
    int schoolId;
    /**
     * 是否删除
     */
    @TableId(value = "is_deleted")
    int isDeleted;
    /**
     * 创建时间
     */
    @TableId(value = "create_time")
    Date createTime;
    /**
     * 更新时间
     */
    @TableId(value = "update_time")
    Date updateTime;
    /**
     * 创建者id
     */
    @TableId(value = "create_user_id")
    int createUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }
}
