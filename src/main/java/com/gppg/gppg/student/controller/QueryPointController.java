package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.student.entity.dto.StudentPointDto;
import com.gppg.gppg.student.entity.exception.CommonException;
import com.gppg.gppg.student.service.IQueryPointService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Yang
 * date: 2020/9/3 10:22
 * des:
 */
@Slf4j
@RestController
@RequestMapping("/student")
public class QueryPointController {

    @Autowired
    IQueryPointService iQueryPointService;

    /**
     * 学生查询自身积分情况
     *
     * @return
     */
    @RequestMapping(value = "/queryPoint", method = RequestMethod.GET)
    public HttpResponse queryPoint(HttpServletRequest request) {
        HttpResponse response = new HttpResponse();

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

        int id = frontUser.getId();
        StudentPointDto studentPointDto = null;
        try {
            studentPointDto = iQueryPointService.studentQueryPoint(id);
        } catch (CommonException exception) {
            return new HttpResponse(exception.getType(),exception.getMessage());
        }
        response.setHttpResponse(ResponseType.SUCCESS, studentPointDto);
        return response;
    }
}
