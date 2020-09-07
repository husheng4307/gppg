package com.gppg.gppg.administrators.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gppg.gppg.administrators.entity.dto.ExchangedPointDto;
import com.gppg.gppg.administrators.entity.dto.SumPointDto;
import com.gppg.gppg.administrators.entity.vo.ExchangeApplyVo;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/7 8:33
 * des:
 */
public interface QueryInfoService extends IService<ExchangeApplyVo> {
    /**
     * 管理员查询本校学生兑换申请信息
     * @param schoolId
     * @param isApproved
     * @return
     */
    List<ExchangeApplyVo> exchangeApply(int schoolId, Integer isApproved);

    /**
     * 查询  daysAgo 天之内的本校各个学院分数
     * @param schoolId
     * @param daysAgo
     * @return
     */
    List<SumPointDto> querySumPoint(int schoolId, int daysAgo);

    /**
     * 查询  daysAgo 天之内的本校各个学院已用积分
     * @param schoolId
     * @param daysAgo
     * @return
     */
    List<ExchangedPointDto> queryExchangePoint(int schoolId, int daysAgo);
}
