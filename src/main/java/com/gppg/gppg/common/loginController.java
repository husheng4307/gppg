package com.gppg.gppg.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.WxyhDomain;
import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.common.mapper.WxyhMapper;
import com.gppg.gppg.common.shiro.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gppg.gppg.common.util.WechatUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/front")
public class loginController {

    @Resource
    private WxyhMapper wxyhMapper;


    /**
     * @author JundaZhu
     * 测试使用shiro的登陆方式
     */
    @PostMapping(value = "/login/shiro")
    @ResponseBody
    public HttpResponse testShiroLogin(@RequestParam(name = "userName", required = false) String userName,
                                       @RequestParam(name = "password", required = false) String password,
                                       @RequestParam(value = "code", required = false) String code,
                                       @RequestParam(value = "rawData", required = false) String rawData,
                                       @RequestParam(value = "loginType", required = true) Integer loginType){
        if(loginType.equals(1)) {
            Subject subject = SecurityUtils.getSubject();
            UserToken token = new UserToken(userName, password,"WebFront");
            HttpResponse response = new HttpResponse();
            try {
                subject.login(token);
                FrontUserDomain principal = (FrontUserDomain) subject.getPrincipal();

                subject.getSession().setAttribute("id", userName);
                response.setHttpResponse(ResponseType.SUCCESS, principal);
                return response;
            } catch (Exception e) {
                response.setHttpResponse(ResponseType.ILLEGAL_ACCOUNT,null);
                return response;
            }
        }
        else{
            HttpResponse response = new HttpResponse();

            // 用户非敏感信息：rawData
            // 签名：signature
            log.info("rawData = " + rawData);
            JSONObject rawDataJson = null;
            try {
                rawDataJson = JSON.parseObject(rawData);
            } catch (Exception e) {
                response.setHttpResponse(ResponseType.UNKNOWN,"json转换");
                return response;
            }

            // 1.接收小程序发送的code
            // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
            System.out.println("code = "+ code);
            JSONObject SessionKeyOpenId = null;
            try {
                SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
            } catch (Exception e) {
                response.setHttpResponse(ResponseType.UNKNOWN,"获取SessionKey、OpenId失败");
                return response;
            }

            // 3.接收微信接口服务 获取返回的参数
            String openid = SessionKeyOpenId.getString("openid");
            System.out.println("openid = " + openid);
            String sessionKey = SessionKeyOpenId.getString("session_key");
            System.out.println("session_key = " + sessionKey);

            // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
//        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
//        if (!signature.equals(signature2)) {
//            return GlobalResult.build(500, "签名校验失败", null);
//        }

            // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
            WxyhDomain user = this.wxyhMapper.selectById(openid);

            // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
            String skey = UUID.randomUUID().toString();



            if (user == null) {
                // 用户信息入库
                String nickName = null;
                String avatarUrl = null;
                String gender = null;
                String city = null;
                String country = null;
                String province = null;
                try {
                    nickName = rawDataJson.getString("nickName");
                    avatarUrl = rawDataJson.getString("avatarUrl");
                    gender = rawDataJson.getString("gender");
                    city = rawDataJson.getString("city");
                    country = rawDataJson.getString("country");
                    province = rawDataJson.getString("province");
                } catch (Exception e) {
                    response.setHttpResponse(ResponseType.UNKNOWN,"rawDataJson失效");
                    return response;
                }

                //数据封装
                user = new WxyhDomain();
                user.setOpenId(openid);
                user.setSkey(skey);
                user.setCreateTime(new Date());
                user.setLastVisitTime(new Date());
                user.setSessionKey(sessionKey);
                user.setCity(city);
                user.setProvince(province);
                user.setCountry(country);
                user.setAvatarUrl(avatarUrl);
                user.setGender(Integer.parseInt(gender));
                user.setNickName(nickName);

                log.info("user = " + user);

                try {
                    this.wxyhMapper.insert(user);
                } catch (Exception e) {
                    response.setHttpResponse(ResponseType.UNKNOWN,"插入微信用户表失败");
                    return response;
                }

                //查询用户是否注册过 QDYH
                FrontUserDomain qdyh = wxyhMapper.getFrontuserByOpenId(openid);
                log.info(qdyh.toString());

                //前端用户未注册，返回openid
                if (qdyh == null) {
                    response.setHttpResponse(ResponseType.SUCCESS_MISSING_NECESSARY_ROLE,openid);
                    return response;
                }else{
                    //前端用户已注册
                    response.setHttpResponse(ResponseType.FAILED,"前端用户已注册，但未授权微信！");
                    return response;
                }

            } else {
                // 已存在，更新用户登录时间
                user.setLastVisitTime(new Date());
                // 重新设置会话skey
                user.setSkey(skey);
                // 重新设置session-key
                user.setSessionKey(sessionKey);

                this.wxyhMapper.updateById(user);

                //查询前端用户注册情况
                FrontUserDomain qdyh1 = wxyhMapper.getFrontuserByOpenId(openid);
                log.info(String.valueOf(qdyh1));

                //前端用户未注册，返回skey
                if (qdyh1 == null) {
                    response.setHttpResponse(ResponseType.SUCCESS_MISSING_NECESSARY_ROLE,openid);
                    return response;
                }

                //前端用户已注册，更新QDYH表中skey，上次登录时间
                Subject subject = SecurityUtils.getSubject();
                UserToken token = new UserToken(qdyh1.getStudentId(), qdyh1.getPassword(),"WXFront");
                try {
                    subject.login(token);
                } catch (Exception e) {
                    response.setHttpResponse(ResponseType.ILLEGAL_ACCOUNT, "/apt/front/login/toLogin");
                    return response;
                }

//                subject.getSession().setAttribute("id", qdyh1.getQDYH_ZH());

                //返回结果
                response.setHttpResponse(ResponseType.SUCCESS,openid);
                return response;
            }

        }
    }
}
