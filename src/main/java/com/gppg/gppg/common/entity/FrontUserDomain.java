package com.gppg.gppg.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
* @Dessciption: 前端用户实体类
* @author: husheng
* @date: 2020/9/3 16:53
*/
@TableName("front_user")
@Data
public class FrontUserDomain implements Serializable {
    private static final long serialVersionUID = 5026666L;

    @TableId(value = "id" , type = IdType.AUTO)
    private int id;

    @TableField("name")
    private String name;

    @TableField("school_id")
    private int schoolId;

    @TableField("academy_id")
    private int academyId;

    @TableField(value = "phone_number")
    private String phoneNumber;

    @TableField("open_id")
    private String openId;

    @TableField("is_deleted")
    private int tagDeleted;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("password")
    private String password;

    @TableField("salt")
    private String salt;

    public FrontUserDomain(){

    }

    public FrontUserDomain(String name, int schoolId, int academyId, String phoneNumber, String openId, String password, String salt) {
        this.name = name;
        this.schoolId = schoolId;
        this.academyId = academyId;
        this.phoneNumber = phoneNumber;
        this.openId = openId;
        this.password = password;
        this.salt = salt;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getAcademyId() {
        return academyId;
    }

    public void setAcademyId(int academyId) {
        this.academyId = academyId;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getTagDeleted() {
        return tagDeleted;
    }

    public void setTagDeleted(int tagDeleted) {
        this.tagDeleted = tagDeleted;
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
}
