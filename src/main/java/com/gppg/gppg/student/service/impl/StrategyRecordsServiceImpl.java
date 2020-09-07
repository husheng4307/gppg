package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.StrategyRecordsDomain;
import com.gppg.gppg.student.entity.dto.StrategyRecordsDto;
import com.gppg.gppg.student.mapper.QueryExchangeProgress;
import com.gppg.gppg.student.service.IStrategyRecordsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 15:19
 * des:
 */
@Service
public class StrategyRecordsServiceImpl extends ServiceImpl<QueryExchangeProgress, StrategyRecordsDomain> implements IStrategyRecordsService{

    /**
     * 学生查询兑换信息以及进度
     * @param id 前端用户id
     * @return
     */
    @Override
    public List<StrategyRecordsDto> queryExchangeProgress(int id) {
        List<StrategyRecordsDto> list = new ArrayList<>();
        try {
            list = this.baseMapper.queryExchangeProgress(id);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
