package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.student.entity.dto.QueryStrategyDto;
import com.gppg.gppg.student.service.IQueryStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 11:24
 * des:查看对应学校积分兑换策略
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class QueryStrategyController {

    @Autowired
    IQueryStrategyService iQueryStrategyService;

    @RequestMapping(value = "/queryStrategy", method = RequestMethod.GET)
    public HttpResponse queryPoint(HttpServletRequest request) {
        HttpResponse response = new HttpResponse();
        List<QueryStrategyDto> list = new ArrayList<>();

        //获取前端用户信息
        Subject subject = SecurityUtils.getSubject();
        FrontUserDomain frontUser = (FrontUserDomain)subject.getPrincipal();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            log.info("Cookie is not null");
            for (Cookie cookie : cookies) {
                log.info(cookie.getValue());
            }
        } else {
            log.info("Cookie is null");
        }

        if (frontUser == null) {
            response.setHttpResponse(ResponseType.NOTEXIST,"学生信息不存在");
            return response;
        }

        int schoolId = frontUser.getSchoolId();
        list = iQueryStrategyService.queryStrategy(schoolId);
        if (list == null) {
            return new HttpResponse(ResponseType.FAILED, "操作失败");
        }
        response.setHttpResponse(ResponseType.SUCCESS, list);
        return response;
    }
}
