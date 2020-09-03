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

    @TableField(value = "student_id")
    private String studentId;

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

}
