package com.gppg.gppg.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.common.entity.AcademyDomain;
import com.gppg.gppg.common.entity.AllSchoolDomain;
import com.gppg.gppg.student.entity.dto.AcademyDto;
import com.gppg.gppg.student.entity.dto.SchoolDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 15:28
 * des:
 */
public interface RegisterMapper extends BaseMapper<AllSchoolDomain> {
    /**
     * 查询所有学校信息
     * @return
     */
    @Select("select school.id xxid, school_name xxmc\n" +
            "from school\n" +
            "where is_deleted = '0'")
    List<SchoolDto> allSchool();

    /**
     * 查询所有学院信息
     * @return
     */
    @Select("select academy.id xyid, academy.academy_name xymc, academy.school_id xxid\n" +
            "from academy\n" +
            "where is_deleted = '0'")
    List<AcademyDto> allAcademy();
}
