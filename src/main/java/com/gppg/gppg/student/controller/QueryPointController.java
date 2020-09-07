package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.student.entity.dto.StudentPointDto;
import com.gppg.gppg.student.service.IQueryPointService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yang
 * date: 2020/9/3 10:22
 * des:
 */
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
    public HttpResponse queryPoint() {
        HttpResponse response = new HttpResponse();

        //获取前端用户信息
        Subject subject = SecurityUtils.getSubject();
        FrontUserDomain frontUser = (FrontUserDomain)subject.getPrincipal();

        if (frontUser == null) {
            response.setHttpResponse(ResponseType.NOTEXIST,"学生信息不存在");
            return response;
        }
        System.out.println(frontUser);

        int id = frontUser.getId();
        StudentPointDto studentPointDto = iQueryPointService.studentQueryPoint(id);
        if (studentPointDto == null) {
            response.setHttpResponse(ResponseType.FAILED, "操作失败");
        }
        response.setHttpResponse(ResponseType.SUCCESS, studentPointDto);
        return response;
    }
}
