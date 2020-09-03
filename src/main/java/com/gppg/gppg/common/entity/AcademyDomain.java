package com.gppg.gppg.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author: Yang
 * date: 2020/9/3 16:18
 * des:学院表
 */
@TableName("academy")
public class AcademyDomain {
    /**
     * 学院id
     */
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    /**
     * 学校id
     */
    int school_id;
    /**
     * 学院名称
     */
    String academy_name;
    /**
     * 是否删除
     */
    int is_deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getAcademy_name() {
        return academy_name;
    }

    public void setAcademy_name(String academy_name) {
        this.academy_name = academy_name;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }
}
