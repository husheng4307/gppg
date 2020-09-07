package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.student.entity.vo.PersonalInfoVo;
import com.gppg.gppg.student.service.IQueryPointService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/7 16:00
 * des:
 */
@RestController
@RequestMapping("/student")
public class PersonalInfoController {

    @Autowired
    IQueryPointService iQueryPointService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/queryPersonalInfo", method = RequestMethod.GET)
    public HttpResponse personalInfo() {
        HttpResponse response = new HttpResponse();

        //获取前端用户信息
        Subject subject = SecurityUtils.getSubject();
        FrontUserDomain frontUser = (FrontUserDomain)subject.getPrincipal();

        if (frontUser == null) {
            response.setHttpResponse(ResponseType.NOTEXIST,"学生信息不存在");
            return response;
        }

        List<PersonalInfoVo> list = iQueryPointService.queryPersonalInfo(frontUser.getId());
        response.setHttpResponse(ResponseType.SUCCESS, list);

        return response;
    }
}
