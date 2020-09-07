package com.gppg.gppg.administrators.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.entity.ExchangeStrategyDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.common.entity.StrategyRecordsDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.common.service.FrontUserPointService;
import com.gppg.gppg.common.service.PointExchangeRecordsService;
import com.gppg.gppg.common.service.PointExchangeStrategyService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: Yang
 * date: 2020/9/7 10:54
 * des:
 */
@RestController
@RequestMapping("/administrators")
public class ManageInfoController {

    public static final int AGREE = 1;
    public static final int DIS_AGREE = 2;

    @Autowired
    FrontUserPointService frontUserPointService;

    @Autowired
    PointExchangeRecordsService pointExchangeRecordsService;

    @Autowired
    PointExchangeStrategyService pointExchangeStrategyService;

    /**
     * 管理员处理本校学生兑换申请
     *
     * @param frontUserId       前端用户ID
     * @param exchangeRecordsId 兑换记录ID
     * @param isApproved        是否通过  1: 通过  2: 不通过
     * @return
     */
    @RequestMapping(value = "/dealExchangeApply", method = RequestMethod.POST)
    public HttpResponse DealExchangeApply(@RequestParam(value = "frontUserId") int frontUserId,
                                          @RequestParam(value = "exchangeRecordsId") int exchangeRecordsId,
                                          @RequestParam(value = "isApproved") int isApproved) {
        HttpResponse response = new HttpResponse();

        //获取后端用户信息
        Subject subject = SecurityUtils.getSubject();
        BackUserDomain backUser = (BackUserDomain) subject.getPrincipal();

        if (backUser == null) {
            response.setHttpResponse(ResponseType.NOTEXIST, "后端用户不存在");
            return response;
        }


        // 根据兑换记录id查询相关信息
        QueryWrapper<StrategyRecordsDomain> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id", exchangeRecordsId);
        StrategyRecordsDomain strategyRecords = pointExchangeRecordsService.getOne(wrapper1);
        if (strategyRecords == null) {
            return new HttpResponse(ResponseType.ILLEGAL_ID, "兑换记录ID不存在");
        }
        // 获得策略id, 查询策略所需积分
        int strategyId = strategyRecords.getPointExchangeStrategy();
        // 获得兑换数量
        int goodsNum = strategyRecords.getCountApplication();

        QueryWrapper<ExchangeStrategyDomain> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("id", strategyId);
        ExchangeStrategyDomain exchangeStrategy = pointExchangeStrategyService.getOne(wrapper2);
        int goodsPoint = exchangeStrategy.getPointAccquired();

        // 计算兑换所需积分
        int sumPoint = goodsPoint * goodsNum;

        // 查询该前端用户可用积分
        QueryWrapper<FrontUserPointsDomain> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("front_user_id", frontUserId);
        FrontUserPointsDomain frontUserPoints = frontUserPointService.getOne(wrapper3);
        int availablePoint = frontUserPoints.getPoint() - frontUserPoints.getExchangedPoint();
        // 积分不足
        if (availablePoint < sumPoint) {
            return new HttpResponse(ResponseType.FAILED_INSUFFICIENT_POINT, "积分不足");
        }

        // 管理员拒绝申请, 返还积分,并在兑换记录表中更新
        if (isApproved == DIS_AGREE) {
            frontUserPoints.setExchangedPoint(frontUserPoints.getExchangedPoint() + sumPoint);
            strategyRecords.setIsApproved(DIS_AGREE);
            strategyRecords.setBackUserId(backUser.getId());
            strategyRecords.setTimeApproved(new Date());
            pointExchangeRecordsService.updateById(strategyRecords);
            frontUserPointService.updateById(frontUserPoints);
        } else {
            // 管理员同意申请
            strategyRecords.setIsApproved(AGREE);
            strategyRecords.setBackUserId(backUser.getId());
            strategyRecords.setTimeApproved(new Date());
            pointExchangeRecordsService.updateById(strategyRecords);
        }
        response.setHttpResponse(ResponseType.SUCCESS, "操作成功");
        return response;
    }

    public HttpResponse updateStrategy() {
        HttpResponse response = new HttpResponse();
        return response;
    }
}
