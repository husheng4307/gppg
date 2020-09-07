package com.gppg.gppg.administrators.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.administrators.entity.dto.ExchangedPointDto;
import com.gppg.gppg.administrators.entity.dto.SumPointDto;
import com.gppg.gppg.administrators.entity.vo.ExchangeApplyVo;
import com.gppg.gppg.administrators.mapper.QueryInfoMapper;
import com.gppg.gppg.administrators.service.QueryInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/7 8:33
 * des:
 */
@Service
public class QueryInfoServiceImpl extends ServiceImpl<QueryInfoMapper, ExchangeApplyVo> implements QueryInfoService {

    /**
     * 管理员查询本校学生兑换申请信息
     *
     * @param schoolId
     * @param isApproved
     * @return
     */
    @Override
    public List<ExchangeApplyVo> exchangeApply(int schoolId, Integer isApproved) {
        List<ExchangeApplyVo> list = this.baseMapper.exchangeApply(schoolId, isApproved);
        System.out.println(list);
        return list;
    }

    /**
     * 查询  daysAgo 天之内的本校各个学院分数
     *
     * @param schoolId
     * @param daysAgo
     * @return
     */
    @Override
    public List<SumPointDto> querySumPoint(int schoolId, int daysAgo) {
        List<SumPointDto> sumPoint = this.baseMapper.querySumPoint(schoolId, daysAgo);
        System.out.println(sumPoint);
        return sumPoint;
    }

    /**
     * 查询  daysAgo 天之内的本校各个学院已用积分
     *
     * @param schoolId
     * @param daysAgo
     * @return
     */
    @Override
    public List<ExchangedPointDto> queryExchangePoint(int schoolId, int daysAgo) {
        List<ExchangedPointDto> exchangePoint = this.baseMapper.queryExchangePoint(schoolId, daysAgo);
        System.out.println(exchangePoint);
        return exchangePoint;
    }
}
