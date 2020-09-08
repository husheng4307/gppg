package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.student.entity.vo.PersonalInfoVo;
import com.gppg.gppg.student.service.IQueryPointService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/7 16:00
 * des:
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class PersonalInfoController {

    @Autowired
    IQueryPointService iQueryPointService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/queryPersonalInfo", method = RequestMethod.GET)
    public HttpResponse personalInfo(HttpServletRequest request) {
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

        List<PersonalInfoVo> list = iQueryPointService.queryPersonalInfo(frontUser.getId());
        if (list == null) {
            response.setHttpResponse(ResponseType.FAILED, "查询前端用户失败");
        }
        response.setHttpResponse(ResponseType.SUCCESS, list);

        return response;
    }
}
