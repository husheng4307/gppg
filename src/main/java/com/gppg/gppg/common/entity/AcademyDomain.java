package com.gppg.gppg.student.entity;

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
}
