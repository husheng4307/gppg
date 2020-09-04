package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.student.entity.dto.QueryStrategyDto;
import com.gppg.gppg.student.service.IQueryStrategyService;
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
 * date: 2020/9/3 11:24
 * des:查看对应学校积分兑换策略
 */
@RestController
@RequestMapping("/student")
public class QueryStrategyController {

    @Autowired
    IQueryStrategyService iQueryStrategyService;

    @RequestMapping(value = "/queryStrategy", method = RequestMethod.GET)
    public HttpResponse queryPoint() {
        HttpResponse response = new HttpResponse();
        List<QueryStrategyDto> list = new ArrayList<>();

        //获取前端用户信息
        Subject subject = SecurityUtils.getSubject();
        FrontUserDomain frontUser = (FrontUserDomain)subject.getPrincipal();

        if (frontUser == null) {
            response.setHttpResponse(ResponseType.NOTEXIST,"学生信息不存在");
            return response;
        }

        int schoolId = frontUser.getSchoolId();
        list = iQueryStrategyService.queryStrategy(schoolId);
        if (list == null) {

        }
        response.setHttpResponse(ResponseType.SUCCESS, list);
        return response;
    }
}
