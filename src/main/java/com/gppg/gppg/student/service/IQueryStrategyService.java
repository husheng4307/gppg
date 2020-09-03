package com.gppg.gppg.student.service;

import com.gppg.gppg.student.entity.dto.QueryStrategyDto;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 10:57
 * des:
 */
public interface IQueryStrategyService {
    /**
     * 查看对应学校积分兑换策略
     * @param schoolId
     * @return
     */
    List<QueryStrategyDto> queryStrategy(int schoolId);
}
