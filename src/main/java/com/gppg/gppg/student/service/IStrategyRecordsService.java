package com.gppg.gppg.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gppg.gppg.common.entity.StrategyRecordsDomain;
import com.gppg.gppg.student.entity.dto.StrategyRecordsDto;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 15:17
 * des:
 */
public interface IStrategyRecordsService extends IService<StrategyRecordsDomain> {
    /**
     * 学生查询兑换记录
     * @param id 前端用户id
     * @return
     */
    List<StrategyRecordsDto> queryExchangeProgress(int id);
}
