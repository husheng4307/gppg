package com.gppg.gppg.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.student.entity.StudentPointDomain;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 9:41
 * des:
 */
public interface QueryPoint extends BaseMapper<StudentPointDomain> {

    /**
     * 学生查询自身积分
     * @param id
     * @return
     */
    @Select("select point, exchanged_point\n" +
            "from front_user_points where front_user_id = #{id}")
    StudentPointDomain queryStudentPoint(int id);
}
