package com.gppg.gppg.administrators.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.administrators.entity.dto.CountInfoAcademyDto;
import com.gppg.gppg.administrators.entity.dto.CountInfoSchoolDto;
import com.gppg.gppg.administrators.entity.dto.ExchangedPointDto;
import com.gppg.gppg.administrators.entity.dto.SumPointDto;
import com.gppg.gppg.administrators.entity.vo.ExchangeApplyVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/7 8:33
 * des:
 */
public interface QueryInfoMapper extends BaseMapper<ExchangeApplyVo> {
    /**
     * 管理员查询本校学生兑换申请信息
     *
     * @param schoolId   学校id
     * @param isApproved 申请状态
     * @return
     */
    @Select({"<script>select a.name applicantName, a.id frontUserId, a.phone_number phoneNumber, c.strategy_name goodsName, b.id exchangeRecordsId, b.count_application goodsNum, b.is_approved applyState, b.time_application applyTime \n" +
            "from front_user a,\n" +
            "     point_exchange_records b,\n" +
            "     point_exchange_strategy c\n" +
            "where a.id = b.front_user_id\n" +
            "  and b.point_exchange_strategy = c.id\n" +
            "  and a.school_id = #{schoolId} \n" +
            "  <if test = \" isApproved != null \">" +
            "and b.is_approved = #{isApproved}" +
            "</if> order by b.time_application desc </script>"})
    List<ExchangeApplyVo> exchangeApply(@Param("schoolId") int schoolId, @Param("isApproved") Integer isApproved);

    /**
     * 查询  daysAgo 天之内的本校各个学院分数
     *
     * @param schoolId
     * @param daysAgo
     * @return
     */
    @Select("select sum(a.point) sumPoint, b.academy_id academyId, d.academy_name academyName\n" +
            "from front_user_points as a\n" +
            "         left join front_user as b on a.front_user_id = b.id\n" +
            "         left join point_records c on b.id = c.front_user_id\n" +
            "         left join academy d on b.academy_id = d.id\n" +
            "where b.school_id = #{schoolId}\n" +
            "  and date_format(c.create_time, '%Y-%m-%d') >= ADDDATE(curdate(), interval -#{daysAgo} day)\n" +
            "group by academyId, academyName\n" +
            "order by sumPoint")
    List<SumPointDto> querySumPoint(int schoolId, int daysAgo);

    /**
     * 查询  daysAgo 天之内的本校各个学院已用积分
     *
     * @param schoolId
     * @param daysAgo
     * @return
     */
    @Select("select sum(a.exchanged_point) exchangedPoint, b.academy_id academyId, d.academy_name academyName\n" +
            "from front_user_points as a\n" +
            "         left join front_user as b on a.front_user_id = b.id\n" +
            "         left join point_exchange_records c on b.id = c.front_user_id\n" +
            "         left join academy d on b.academy_id = d.id\n" +
            "where b.school_id = #{schoolId}\n" +
            "  and date_format(c.time_application, '%Y-%m-%d') >= ADDDATE(curdate(), interval -#{daysAgo} day)\n" +
            "group by academyId, academyName\n" +
            "order by exchangedPoint")
    List<ExchangedPointDto> queryExchangePoint(int schoolId, int daysAgo);


    /**
     * 查询  daysAgo 天之内的本校各个学院获得积分、使用积分
     *
     * @param schoolId
     * @param daysAgo
     * @return
     */
    @Select("select p.sumPoint, ifnull(q.usedPoint, 0) usedPoint, p.sumStudent, p.academyId, p.academyName\n" +
            "from (select i.sumPoint, j.sumStudent, i.academyId, i.academyName\n" +
            "      from (select count(pr.id) sumPoint, fu.academy_id academyId, a.academy_name academyName\n" +
            "            from point_records pr,\n" +
            "                 front_user fu,\n" +
            "                 academy a\n" +
            "            where pr.front_user_id = fu.id\n" +
            "              and fu.school_id = #{schoolId}\n" +
            "              and fu.academy_id = a.id\n" +
            "              and date_format(pr.create_time, '%Y-%m-%d') >= ADDDATE(curdate(), interval -#{daysAgo} day)\n" +
            "            group by a.academy_name, fu.academy_id\n" +
            "            order by sumPoint desc) i\n" +
            "               join (select count(fu.academy_id) sumStudent, fu.academy_id, a.academy_name\n" +
            "                     from front_user fu,\n" +
            "                          academy a\n" +
            "                     where fu.academy_id = a.id\n" +
            "                       and fu.school_id = #{schoolId}\n" +
            "                     group by fu.academy_id, a.academy_name\n" +
            "                     order by sumStudent desc) j on i.academyId = j.academy_id) p\n" +
            "         join\n" +
            "     (select sum(per.count_application * pes.point_accquired) usedPoint, a.id, a.academy_name\n" +
            "      from point_exchange_strategy pes,\n" +
            "           point_exchange_records per,\n" +
            "           front_user fu,\n" +
            "           academy a\n" +
            "      where pes.id = per.point_exchange_strategy\n" +
            "        and per.front_user_id = fu.id\n" +
            "        and fu.academy_id = a.id\n" +
            "        and fu.school_id = #{schoolId}\n" +
            "        and date_format(per.time_application, '%Y-%m-%d') >= ADDDATE(curdate(), interval -#{daysAgo} day)\n" +
            "      group by a.id, a.academy_name\n" +
            "      order by usedPoint) q on p.academyId = q.id")
    List<CountInfoAcademyDto> queryCountInfoFromAcademy(int schoolId, int daysAgo);

    /**
     * 查询  daysAgo 天之内的各个学校获得积分、使用积分
     *
     * @param daysAgo
     * @return
     */
    @Select("select p.sumPoint, IFNULL(q.usedPoint, 0) usedPoint, p.sumStudent, p.school_id academyId, p.school_name academyName\n" +
            "from (select j.sumStudent ,i.sumPoint, i.school_id, i.school_name\n" +
            "      from (select count(pr.id) sumPoint, fu.school_id, s.school_name\n" +
            "            from point_records pr,\n" +
            "                 front_user fu,\n" +
            "                 school s\n" +
            "            where pr.front_user_id = fu.id\n" +
            "              and fu.school_id = s.id\n" +
            "              and date_format(pr.create_time, '%Y-%m-%d') >= ADDDATE(curdate(), interval -#{daysAgo} day)\n" +
            "            group by fu.school_id, s.school_name\n" +
            "            order by sumPoint desc) i\n" +
            "              left join\n" +
            "           (select count(fu.school_id) sumStudent, fu.school_id, s.school_name\n" +
            "            from front_user fu,\n" +
            "                 school s\n" +
            "            where fu.school_id = s.id\n" +
            "            group by fu.school_id, s.school_name, s.school_name\n" +
            "            order by sumStudent desc) j on i.school_id = j.school_id) p\n" +
            "         left join\n" +
            "     (select ifnull(sum(per.count_application * pes.point_accquired), 0) usedPoint, s.id, s.school_name\n" +
            "      from point_exchange_strategy pes,\n" +
            "           point_exchange_records per,\n" +
            "           front_user fu,\n" +
            "           school s\n" +
            "      where pes.id = per.point_exchange_strategy\n" +
            "        and per.front_user_id = fu.id\n" +
            "        and fu.school_id = s.id\n" +
            "        and date_format(per.time_application, '%Y-%m-%d') >= ADDDATE(curdate(), interval -#{daysAgo} day)\n" +
            "      group by s.id, s.school_name\n" +
            "      order by usedPoint) q on p.school_id = q.id")
    List<CountInfoAcademyDto> queryCountInfoFromSchool(int daysAgo);
}
