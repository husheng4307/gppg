package com.gppg.gppg.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.student.entity.dto.StudentPointDto;
import com.gppg.gppg.student.entity.vo.PersonalInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 9:41
 * des:
 */
public interface QueryPoint extends BaseMapper<FrontUserPointsDomain> {

    /**
     * 学生查询自身积分
     *
     * @param id
     * @return
     */
    @Select("select *\n" +
            "from front_user_points where front_user_id = #{id}")
    FrontUserPointsDomain queryStudentPoint(@Param("id") int id);

    /**
     * 学生查询自身信息
     *
     * @param userId
     * @return
     */
    @Select("select a.name userName, b.point userPoint, b.exchanged_point userExchangedPoint, c.academy_name academyName, d.school_name schoolName\n" +
            "from front_user a,\n" +
            "     front_user_points b,\n" +
            "     academy c,\n" +
            "     school d\n" +
            "where a.school_id = d.id\n" +
            "  and a.academy_id = c.id\n" +
            "  and a.id = b.front_user_id\n" +
            "  and a.id = #{userId}")
    List<PersonalInfoVo> queryPersonalInfo(int userId);
}
