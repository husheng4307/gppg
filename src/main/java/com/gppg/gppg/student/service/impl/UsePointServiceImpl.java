package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.*;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.common.service.FrontUserPointService;
import com.gppg.gppg.student.entity.exception.CommonException;
import com.gppg.gppg.student.mapper.UsePointMapper;
import com.gppg.gppg.student.service.IExchangesStrategyService;
import com.gppg.gppg.student.service.IStrategyRecordsService;
import com.gppg.gppg.student.service.IUsePointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author: Yang
 * date: 2020/9/3 21:02
 * des:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UsePointServiceImpl extends ServiceImpl<UsePointMapper, PointRecordsDomain> implements IUsePointService {

    @Autowired
    IUsePointService iUsePointService;

    @Autowired
    IExchangesStrategyService iExchangesStrategyService;

    @Autowired
    FrontUserPointService iFrontUserPointService;

    @Autowired
    IStrategyRecordsService iStrategyRecordsService;

    /**
     * 学生使用积分申请兑换
     * @param frontUser
     * @param id
     * @param sum
     */
    @Override
    public void studentUsePoint(FrontUserDomain frontUser, int id, int sum) {
        // 通过兑换策略id查找每一份所需要的积分,计算需要的总积分
        if (sum == 0) {
            throw new CommonException(ResponseType.FAILED, "兑换份数不能为0");
        }
        QueryWrapper<ExchangeStrategyDomain> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        ExchangeStrategyDomain domain = iExchangesStrategyService.getOne(wrapper);
        if (domain == null) {
            throw new CommonException(ResponseType.ILLEGAL_ID, "策略不存在");
        }
        int point = domain.getPointAccquired();
        int needAdd = point * sum;

        // 积分表中 更新 已用积分
        QueryWrapper<FrontUserPointsDomain> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("front_user_id", frontUser.getId());
        //
        FrontUserPointsDomain domain1 = iFrontUserPointService.getOne(wrapper2);
        if (domain1 == null) {
            throw new CommonException(ResponseType.FAILED, "操作失败");
        }
        int finalPoint = domain1.getExchangedPoint() + needAdd;
        if (finalPoint > domain1.getPoint()) {
            throw new CommonException(ResponseType.FAILED_INSUFFICIENT_POINT, null);
        } else {
            try {
                domain1.setExchangedPoint(domain1.getExchangedPoint() + needAdd);
                iFrontUserPointService.updateById(domain1);
                // 兑换记录表新增记录
                log.info("domain1 =" + domain1);
                StrategyRecordsDomain strategyRecordsDomain = new StrategyRecordsDomain(frontUser.getId(), id, sum);
                iStrategyRecordsService.save(strategyRecordsDomain);
                log.info("strategyRecordsDomain =" + strategyRecordsDomain);
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new CommonException(ResponseType.FAILED_DB, null);
            }
        }
    }
}
