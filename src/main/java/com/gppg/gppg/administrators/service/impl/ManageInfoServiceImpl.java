package com.gppg.gppg.administrators.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gppg.gppg.administrators.service.ManageInfoService;
import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.entity.ExchangeStrategyDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.common.entity.StrategyRecordsDomain;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.common.service.FrontUserPointService;
import com.gppg.gppg.common.service.PointExchangeRecordsService;
import com.gppg.gppg.common.service.PointExchangeStrategyService;
import com.gppg.gppg.student.entity.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

/**
 * @author: Yang
 * date: 2020/9/7 9:45
 * des:
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class ManageInfoServiceImpl implements ManageInfoService {

    public static final int AGREE = 1;
    public static final int DIS_AGREE = 2;

    @Autowired
    PointExchangeStrategyService pointExchangeStrategyService;

    @Autowired
    PointExchangeRecordsService pointExchangeRecordsService;

    @Autowired
    FrontUserPointService frontUserPointService;

    @Override
    public void dealStudentApply(int frontUserId, int exchangeRecordsId, int isApproved, BackUserDomain backUser) {
        // 根据兑换记录id查询相关信息
        QueryWrapper<StrategyRecordsDomain> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id", exchangeRecordsId);
        StrategyRecordsDomain strategyRecords = pointExchangeRecordsService.getOne(wrapper1);
        if (strategyRecords == null) {
            throw new CommonException(ResponseType.ILLEGAL_ID, "兑换记录ID不存在");
        }
        // 获得策略id, 查询策略所需积分
        int strategyId = strategyRecords.getPointExchangeStrategy();
        // 获得兑换数量
        int goodsNum = strategyRecords.getCountApplication();
        if (strategyRecords.getIsApproved() != 0) {
            throw new CommonException(ResponseType.FAILED, "该申请已处理，请勿重复提交");
        }

        QueryWrapper<ExchangeStrategyDomain> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("id", strategyId);
        ExchangeStrategyDomain exchangeStrategy = pointExchangeStrategyService.getOne(wrapper2);
        if (exchangeStrategy == null) {
            throw new CommonException(ResponseType.ILLEGAL_ID, "策略ID不存在");
        }
        int goodsPoint = exchangeStrategy.getPointAccquired();

        // 计算兑换所需积分
        int sumPoint = goodsPoint * goodsNum;

        // 查询该前端用户可用积分
        QueryWrapper<FrontUserPointsDomain> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("front_user_id", frontUserId);
        FrontUserPointsDomain frontUserPoints = frontUserPointService.getOne(wrapper3);
        if (frontUserPoints == null) {
            throw new CommonException(ResponseType.ILLEGAL_ID, "前端用户不存在");
        }
        int availablePoint = frontUserPoints.getPoint() - frontUserPoints.getExchangedPoint();
//        // 积分不足
//        if (availablePoint < sumPoint) {
//            throw new CommonException(ResponseType.FAILED_INSUFFICIENT_POINT, "积分不足");
//        }

        try {
            // 管理员拒绝申请, 返还积分,并在兑换记录表中更新
            if (isApproved == DIS_AGREE) {
                frontUserPoints.setExchangedPoint(frontUserPoints.getExchangedPoint() - sumPoint);
                strategyRecords.setIsApproved(DIS_AGREE);
                strategyRecords.setBackUserId(backUser.getId());
                strategyRecords.setTimeApproved(new Date());
                log.info("strategyRecords = " + strategyRecords);
                pointExchangeRecordsService.updateById(strategyRecords);
                log.info("frontUserPoints = " + frontUserPoints);
                frontUserPointService.updateById(frontUserPoints);
            } else {
                // 管理员同意申请
                strategyRecords.setIsApproved(AGREE);
                strategyRecords.setBackUserId(backUser.getId());
                strategyRecords.setTimeApproved(new Date());
                log.info("strategyRecords111 = " + strategyRecords);
                pointExchangeRecordsService.updateById(strategyRecords);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new CommonException(ResponseType.FAILED_DB, null);
        }
//        response.setHttpResponse(ResponseType.SUCCESS, "操作成功");
    }
}
