package com.gppg.gppg.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.student.entity.dto.StrategyRecordsDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 11:30
 * des: 
 */
public interface QueryExchangeProgress extends BaseMapper<StrategyRecordsDto> {
    /**
     * 学生查询兑换记录
     * @param id
     * @return
     */
    @Select("select a.count_application countApplication,\n" +
            "       a.is_approved isApproved,\n" +
            "       a.time_application timeApplication,\n" +
            "       a.time_approved timeApproved,\n" +
            "       b.strategy_name strategyName,\n" +
            "       b.create_time createTime\n" +
            "from point_exchange_records a,\n" +
            "     point_exchange_strategy b\n" +
            "where a.point_exchange_strategy = b.id\n" +
            "  and a.front_user_id = #{id}")
    List<StrategyRecordsDto> queryExchangeProgress(int id);
}
