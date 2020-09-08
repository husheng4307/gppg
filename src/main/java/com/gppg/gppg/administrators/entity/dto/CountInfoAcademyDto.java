package com.gppg.gppg.administrators.entity.dto;

import lombok.Data;

/**
 * @author: Yang
 * date: 2020/9/8 9:23
 * des: 统计信息
 */
@Data
public class CountInfoAcademyDto {
    /**
     * 积分
     */
    int sumPoint;
    /**
     * 已用积分
     */
    int usedPoint;
    /**
     * 总学生人数
     */
    int sumStudent;
    /**
     * id
     */
    int academyId;
    /**
     * 学院/学校 名称
     */
    String academyName;
}
