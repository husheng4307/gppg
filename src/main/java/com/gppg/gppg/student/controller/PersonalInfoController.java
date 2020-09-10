package com.gppg.gppg.student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.common.service.FrontUserPointService;
import com.gppg.gppg.student.entity.exception.CommonException;
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

    @Autowired
    FrontUserPointService iFrontUserPointService;

    /**
     * @return
     */
    @RequestMapping(value = "/queryPersonalInfo", method = RequestMethod.GET)
    public HttpResponse personalInfo(HttpServletRequest request) {
        HttpResponse response = new HttpResponse();

        //获取前端用户信息
        Subject subject = SecurityUtils.getSubject();
        FrontUserDomain frontUser = (FrontUserDomain) subject.getPrincipal();

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
            response.setHttpResponse(ResponseType.NOTEXIST, "学生信息不存在");
            return response;
        }

        try {
            QueryWrapper<FrontUserPointsDomain> wrapper = new QueryWrapper<>();
            wrapper.eq("front_user_id", frontUser.getId());
            FrontUserPointsDomain domain = iFrontUserPointService.getOne(wrapper);
            if (domain == null) {
                FrontUserPointsDomain domain1 = iFrontUserPointService.getOne(wrapper);
                domain1.setFrontUserId(frontUser.getId());
                domain1.setPoint(0);
                domain1.setExchangedPoint(0);
                iFrontUserPointService.save(domain1);
            }
        } catch (Exception e) {
            throw new CommonException(ResponseType.FAILED, "操作失败");
        }

        List<PersonalInfoVo> list = iQueryPointService.queryPersonalInfo(frontUser.getId());

        response.setHttpResponse(ResponseType.SUCCESS, list);

        return response;
    }
}
