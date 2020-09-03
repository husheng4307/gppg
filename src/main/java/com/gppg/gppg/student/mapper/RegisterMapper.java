package com.gppg.gppg.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.student.entity.AllSchoolDomain;
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
    @Select("select *\n" +
            "from school\n" +
            "where is_deleted = '0'")
    List<AllSchoolDomain> allSchool();
}
