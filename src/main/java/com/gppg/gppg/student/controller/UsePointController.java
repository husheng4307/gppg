package com.gppg.gppg.student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gppg.gppg.common.entity.ExchangeStrategyDomain;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.common.entity.StrategyRecordsDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.common.service.FrontUserPointService;
import com.gppg.gppg.student.service.IExchangesStrategyService;
import com.gppg.gppg.student.service.IStrategyRecordsService;
import com.gppg.gppg.student.service.IUsePointService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Yang
 * date: 2020/9/3 20:43
 * des:
 */
@RestController
@RequestMapping("/student")
public class UsePointController {

    @Autowired
    IUsePointService iUsePointService;

    @Autowired
    IExchangesStrategyService iExchangesStrategyService;

    @Autowired
    FrontUserPointService iFrontUserPointService;

    @Autowired
    IStrategyRecordsService iStrategyRecordsService;

    @RequestMapping(value = "/usePoint", method = RequestMethod.POST)
    public HttpResponse httpResponse(@RequestParam("id") int id,
                                     @RequestParam("sum") int sum) {
        HttpResponse response = new HttpResponse();
        // 获取前端用户信息
        Subject subject = SecurityUtils.getSubject();
        FrontUserDomain frontUser = (FrontUserDomain) subject.getPrincipal();

        if (frontUser == null) {
            response.setHttpResponse(ResponseType.NOTEXIST, "学生信息不存在");
            return response;
        }

        // 通过兑换策略id查找每一份所需要的积分,计算需要的总积分
        QueryWrapper<ExchangeStrategyDomain> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        ExchangeStrategyDomain domain = iExchangesStrategyService.getOne(wrapper);
        int point = domain.getPointAccquired();
        int needAdd = point * sum;

        // 积分表中 更新 已用积分
        QueryWrapper<FrontUserPointsDomain> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("front_user_id", frontUser.getId());
        FrontUserPointsDomain domain1 = iFrontUserPointService.getOne(wrapper2);
        if (domain1 == null) {
            return new HttpResponse(ResponseType.FAILED, "操作失败");
        }
        int finalPoint = domain1.getExchangedPoint() + needAdd;
        if (finalPoint > domain1.getPoint()) {
            response.setHttpResponse(ResponseType.FAILED_INSUFFICIENT_POINT, null);
        } else {
            domain1.setExchangedPoint(finalPoint);
            iFrontUserPointService.updateById(domain1);
            // 兑换记录表新增记录
            StrategyRecordsDomain strategyRecordsDomain = new StrategyRecordsDomain(frontUser.getId(), id, sum);
            iStrategyRecordsService.save(strategyRecordsDomain);
            response.setHttpResponse(ResponseType.SUCCESS, null);
        }
        return response;
    }
}
