package com.gppg.gppg.student.entity.vo;

import lombok.Data;

/**
 * @author: Yang
 * date: 2020/9/7 16:04
 * des: 个人信息
 */
@Data
public class PersonalInfoVo {
    String userName;
    int userPoint;
    int userExchangedPoint;
    String academyName;
    String schoolName;
}
