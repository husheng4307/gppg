package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.response.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yang
 * date: 2020/9/3 11:24
 * des:
 */
@RestController
@RequestMapping("/student")
public class QueryStrategyController {
    @RequestMapping(value = "/queryStrategy", method = RequestMethod.GET)
    public HttpResponse queryPoint() {
        HttpResponse httpResponse = new HttpResponse();
        return httpResponse;
    }
}
