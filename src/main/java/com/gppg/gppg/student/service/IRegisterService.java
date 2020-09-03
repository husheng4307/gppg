package com.gppg.gppg.student.service;

import com.gppg.gppg.student.entity.AllSchoolDomain;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 15:35
 * des:
 */
public interface IRegisterService {
    /**
     * 获取所有学校信息
     * @return
     */
    List<AllSchoolDomain> allSchool();
}
