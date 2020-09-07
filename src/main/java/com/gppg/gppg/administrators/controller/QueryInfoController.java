package com.gppg.gppg.administrators.controller;

import com.gppg.gppg.administrators.entity.dto.ExchangedPointDto;
import com.gppg.gppg.administrators.entity.dto.SumPointDto;
import com.gppg.gppg.administrators.entity.vo.CountInfoVo;
import com.gppg.gppg.administrators.entity.vo.ExchangeApplyVo;
import com.gppg.gppg.administrators.service.QueryInfoService;
import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/7 8:23
 * des:
 */
@RestController
@RequestMapping("/administrators")
public class QueryInfoController {

    @Autowired
    QueryInfoService queryInfoService;

    /**
     * @param isApproved
     * @return
     */
    @RequestMapping(value = "/queryExchangeApply", method = RequestMethod.POST)
    public HttpResponse QueryExchangeApply(@RequestParam(required = false) Integer isApproved) {
        HttpResponse response = new HttpResponse();
        List<ExchangeApplyVo> list = new ArrayList<>();

        //获取后端用户信息
        Subject subject = SecurityUtils.getSubject();
        BackUserDomain backUser = (BackUserDomain) subject.getPrincipal();

        if (backUser == null) {
            response.setHttpResponse(ResponseType.NOTEXIST, "后端用户不存在");
            return response;
        }

        if (isApproved == null) {
            list = queryInfoService.exchangeApply(backUser.getSchoolId(), null);
        } else {
            list = queryInfoService.exchangeApply(backUser.getSchoolId(), isApproved);
        }

        return new HttpResponse(ResponseType.SUCCESS, list);
    }

    /**
     * 管理员查询本校统计信息
     *
     * @param daysAgo
     * @return
     */
    @RequestMapping(value = "/queryCountInfo", method = RequestMethod.POST)
    public HttpResponse QueryCountInfo(int daysAgo) {
        HttpResponse response = new HttpResponse();

        //获取后端用户信息
        Subject subject = SecurityUtils.getSubject();
        BackUserDomain backUser = (BackUserDomain) subject.getPrincipal();

        if (backUser == null) {
            response.setHttpResponse(ResponseType.NOTEXIST, "后端用户不存在");
            return response;
        }

        // 拼接结果
        List<SumPointDto> sumPoint = queryInfoService.querySumPoint(backUser.getSchoolId(), daysAgo);
        List<ExchangedPointDto> exchangePoint = queryInfoService.queryExchangePoint(backUser.getSchoolId(), daysAgo);

        return new HttpResponse(ResponseType.SUCCESS, new CountInfoVo(sumPoint, exchangePoint));
    }
}
