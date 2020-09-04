package com.gppg.gppg.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Table;
import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 15:29
 * des:
 */
@TableName("school")
public class AllSchoolDomain {
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    String schoolName;
    int isDeleted;

    @TableField(exist = false)
    List<AcademyDomain> academy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<AcademyDomain> getAcademy() {
        return academy;
    }

    public void setAcademy(List<AcademyDomain> academy) {
        this.academy = academy;
    }
}
