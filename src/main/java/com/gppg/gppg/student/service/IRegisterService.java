package com.gppg.gppg.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gppg.gppg.common.entity.AllSchoolDomain;
import com.gppg.gppg.student.entity.dto.SchoolAndAcademyDto;
import com.gppg.gppg.student.entity.dto.SchoolDto;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 15:35
 * des:
 */
public interface IRegisterService extends IService<AllSchoolDomain> {
    /**
     * 获取所有学校、学院信息
     * @return
     */
    List<SchoolDto> allSchoolAndAcademy();
}
