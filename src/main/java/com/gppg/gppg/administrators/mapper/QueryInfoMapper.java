package com.gppg.gppg.administrators.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.administrators.entity.dto.ExchangedPointDto;
import com.gppg.gppg.administrators.entity.dto.SumPointDto;
import com.gppg.gppg.administrators.entity.vo.ExchangeApplyVo;
import lombok.experimental.Wither;
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
    @Select({"<script>select a.name applicantName, a.id frontUserId, a.phone_number applicantId, c.strategy_name goodsName, b.id exchangeRecordsId, b.count_application goodsNum, b.is_approved applyState, b.time_application applyTime \n" +
            "from front_user a,\n" +
            "     point_exchange_records b,\n" +
            "     point_exchange_strategy c\n" +
            "where a.id = b.front_user_id\n" +
            "  and b.point_exchange_strategy = c.id\n" +
            "  and a.school_id = #{schoolId}\n" +
            "  <if test = \" isApproved != null \">" +
            "and b.is_approved = #{isApproved}"+
            "</if></script>"})
    List<ExchangeApplyVo> exchangeApply(@Param("schoolId") int schoolId, @Param("isApproved") Integer isApproved);

    /**
     * 查询  daysAgo 天之内的本校各个学院分数
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
}
