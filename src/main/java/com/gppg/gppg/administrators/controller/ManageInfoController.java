package com.gppg.gppg.administrators.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gppg.gppg.administrators.service.ManageInfoService;
import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.entity.ExchangeStrategyDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.common.entity.StrategyRecordsDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.common.service.FrontUserPointService;
import com.gppg.gppg.common.service.PointExchangeRecordsService;
import com.gppg.gppg.common.service.PointExchangeStrategyService;
import com.gppg.gppg.student.entity.exception.CommonException;
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

    @Autowired
    ManageInfoService infoService;

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

        /**
         * 处理学生发起的兑换申请
         */
        try {
            infoService.dealStudentApply(frontUserId, exchangeRecordsId, isApproved, backUser);
        } catch (CommonException e) {
            return new HttpResponse(e.getType(), e.getMessage());
        }
        return new HttpResponse(ResponseType.SUCCESS, null);
    }

    /**
     * 管理员更新策略
     * @return
     */
    public HttpResponse updateStrategy() {
        HttpResponse response = new HttpResponse();


        return response;
    }
}
