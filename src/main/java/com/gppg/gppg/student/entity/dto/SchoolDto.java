package com.gppg.gppg.student.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/8 22:41
 * des:
 */
@Data
public class SchoolDto {
    int xxid;
    String xxmc;
    List<AcademyDto> academy;
}
