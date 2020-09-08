package com.gppg.gppg.student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gppg.gppg.common.entity.AllSchoolDomain;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.WxyhDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.common.mapper.WxyhMapper;
import com.gppg.gppg.common.service.FrontUserService;
import com.gppg.gppg.common.shiro.UserToken;
import com.gppg.gppg.common.util.MD5;
import com.gppg.gppg.student.entity.dto.SchoolAndAcademyDto;
import com.gppg.gppg.student.entity.dto.SchoolDto;
import com.gppg.gppg.student.service.IRegisterService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    WxyhMapper wxyhMapper;

    @Autowired
    FrontUserService frontUserService;

    /**
     * @return
     */
    @RequestMapping(value = "/allSchoolAndAcademy", method = RequestMethod.GET)
    public HttpResponse allSchool() {
        // 定义返回
        HttpResponse response = new HttpResponse();
        List<SchoolDto> list = new LinkedList();
        list = iRegisterService.allSchoolAndAcademy();
        if (list == null) {
            response.setHttpResponse(ResponseType.FAILED, "获取信息失败");
            return response;
        }
        response.setHttpResponse(ResponseType.SUCCESS, list);
        return response;
    }

    /**
     * @Author: husheng
     * @param： * @param null
     * @return：
     * @Description: 前端用户注册
     * @Date: 下午7:40 19-10-24
     */
    @RequiresAuthentication
    @PostMapping("/register")
    @ResponseBody
    public HttpResponse qdyhZhuce(
            @RequestParam("name") String name,
            @RequestParam("schoolId") int schoolId,
            @RequestParam("academyId") int academyId,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("openid") String openid) {

        //定义通用类
        HttpResponse response = new HttpResponse();

        //获取微信用户信息
        WxyhDomain wxyhDomain = wxyhMapper.selectById(openid);

        //如果该用户曾经注册过前端用户，则绑定其微信openid 至 该前端用户
        QueryWrapper<FrontUserDomain> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_number", phoneNumber);
        FrontUserDomain frontUser = frontUserService.getOne(wrapper);
        if (frontUser != null) {
            frontUser.setOpenId(openid);

            try {
                frontUserService.updateById(frontUser);
            } catch (Exception e) {
                response.setHttpResponse(ResponseType.FAILED_UPDATE_FRONTUSER, null);
                return response;
            }

            //前端用户已注册，注册完后登录
            Subject subject = SecurityUtils.getSubject();
            UserToken token = new UserToken(frontUser.getphoneNumber(), frontUser.getPassword(), "WXFront");
            try {
                subject.login(token);
            } catch (Exception e) {
                response.setHttpResponse(ResponseType.ILLEGAL_ACCOUNT, null);
                return response;
            }

            response.setHttpResponse(ResponseType.SUCCESS, "绑定前端用户成功");
            return response;
        }

//        String sr = DateUtil.converSfz2Sr(sfz);
        String mm = "123456";
        //生成随机字符串盐
        String salt = MD5.randomString();
        //加盐
        String passWithSalt = MD5.md5(mm, salt);
//        FrontUserDomain frontUserDomain = new FrontUserDomain(wxyhDomain, zh, passWithSalt, salt, convert2Date, sfz, xm, dh);
        FrontUserDomain frontUserDomain = new FrontUserDomain(name, schoolId, academyId, phoneNumber, openid, passWithSalt, salt);

        try {
            frontUserService.save(frontUserDomain);
            response.setHttpResponse(ResponseType.SUCCESS, "注册成功");
            System.out.println(frontUserDomain);
        } catch (Exception e) {
            response.setHttpResponse(ResponseType.FAILED, "前端用户新增失败");
            return response;
        }

        //前端用户已注册，更新QDYH表中skey，上次登录时间
//        Subject subject = SecurityUtils.getSubject();
//        UserToken token = new UserToken(frontUserDomain.getQDYH_ZH(), frontUserDomain.getQDYH_MM(), "WXFront");
//        try {
//            // 登录
//            subject.login(token);
//        } catch (Exception e) {
//            // 登陆失败
//            response.setHttpResponse(ResponseType.ILLEGAL_ACCOUNT, null);
//            return response;
//        }

        response.setHttpResponse(ResponseType.SUCCESS, null);
        return response;
    }

}
