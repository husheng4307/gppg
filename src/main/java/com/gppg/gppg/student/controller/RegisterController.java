package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.student.entity.AllSchoolDomain;
import com.gppg.gppg.student.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 15:45
 * des:
 */
@RestController
@RequestMapping("/student")
public class RegisterController {

    @Autowired
    IRegisterService iRegisterService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/allSchool", method = RequestMethod.GET)
    public HttpResponse allSchool() {
        // 定义返回
        HttpResponse response = new HttpResponse();
        List<AllSchoolDomain> list = new LinkedList();
        list = iRegisterService.allSchool();
        if (list == null) {
            response.setHttpResponse(ResponseType.FAILED, "");
            return response;
        }
        response.setHttpResponse(ResponseType.SUCCESS, list);
        return response;
    }
}
