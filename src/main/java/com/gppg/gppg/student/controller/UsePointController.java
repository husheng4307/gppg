package com.gppg.gppg.student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gppg.gppg.common.entity.ExchangeStrategyDomain;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.common.entity.StrategyRecordsDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.common.service.FrontUserPointService;
import com.gppg.gppg.student.entity.exception.CommonException;
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

        try {
            iUsePointService.studentUsePoint(frontUser, id, sum);
        } catch (CommonException e) {
            return new HttpResponse(e.getType(), e.getMessage());
        }
        return new HttpResponse(ResponseType.SUCCESS, null);
    }
}
