package com.gppg.gppg.student.controller;

import com.gppg.gppg.common.entity.response.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yang
 * date: 2020/9/3 15:23
 * des:
 */
@RestController
@RequestMapping("/student")
public class StrategyRecordsController {
    @RequestMapping(value = "/strategyRecords", method = RequestMethod.GET)
    public HttpResponse strategyRecords() {
        HttpResponse httpResponse = new HttpResponse();
        return httpResponse;
    }
}
