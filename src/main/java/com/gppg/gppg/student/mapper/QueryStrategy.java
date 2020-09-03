package com.gppg.gppg.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gppg.gppg.common.entity.ExchangeStrategyDomain;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 10:42
 * des:
 */
public interface QueryStrategy extends BaseMapper<ExchangeStrategyDomain> {
    /**
     * 查询积分兑换策略
     * @param schoolId
     * @return
     */
    @Select("select strategy_name, strategy_description, point_accquired\n" +
            "from point_exchange_strategy\n" +
            "where school_id = #{schoolId}")
    List<ExchangeStrategyDomain> queryStrategy(int schoolId);
}
