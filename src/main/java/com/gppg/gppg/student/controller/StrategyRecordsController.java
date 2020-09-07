package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.student.entity.dto.StrategyRecordsDto;
import com.gppg.gppg.student.service.IStrategyRecordsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 15:23
 * des: 查询积分兑换
 */
@RestController
@RequestMapping("/student")
public class StrategyRecordsController {

    @Autowired
    IStrategyRecordsService iStrategyRecordsService;

    @RequestMapping(value = "/strategyRecords", method = RequestMethod.GET)
    public HttpResponse strategyRecords() {
        List<StrategyRecordsDto> data = new ArrayList<>();
        HttpResponse response = new HttpResponse();
        //获取前端用户信息
        Subject subject = SecurityUtils.getSubject();
        FrontUserDomain frontUser = (FrontUserDomain)subject.getPrincipal();

        if (frontUser == null) {
            response.setHttpResponse(ResponseType.NOTEXIST,"学生信息不存在");
            return response;
        }

        data = iStrategyRecordsService.queryExchangeProgress(frontUser.getId());

        response.setHttpResponse(ResponseType.SUCCESS, data);
        return response;
    }
}
