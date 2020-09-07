package com.gppg.gppg.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: Yang
 * date: 2020/9/3 16:18
 * des:学院表
 */
@Data
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
    @TableField(value = "school_id")
    int schoolId;
    /**
     * 学院名称
     */
    @TableField(value = "academy_name")
    String academyName;
    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    int isDeleted;

}
