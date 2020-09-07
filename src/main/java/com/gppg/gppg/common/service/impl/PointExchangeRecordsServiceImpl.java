package com.gppg.gppg.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.ExchangeStrategyDomain;
import com.gppg.gppg.common.entity.StrategyRecordsDomain;
import com.gppg.gppg.common.mapper.PointExchangeRecordsMapper;
import com.gppg.gppg.common.service.PointExchangeRecordsService;
import org.springframework.stereotype.Service;

/**
 * @author: Yang
 * date: 2020/9/6 16:47
 * des:
 */
@Service
public class PointExchangeRecordsServiceImpl extends ServiceImpl<PointExchangeRecordsMapper, StrategyRecordsDomain> implements PointExchangeRecordsService {
}
