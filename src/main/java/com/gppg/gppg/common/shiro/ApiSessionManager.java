package com.gppg.gppg.common.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 重写Shiro SessionId的获取方式，以适应前后端分离场景
 *
 * @author BeanYon
 * 2019.08.01
 */
public class ApiSessionManager extends DefaultWebSessionManager {
    /**
     * SessionId，从前端请求的header中读取
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * SessionId引用源
     */
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public ApiSessionManager() {
        super();
    }

    /**
     * 获取SessionId
     *
     * @param request  前端请求
     * @param response 响应
     * @return sessionId
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        // 请求头中有 Authorization 字段，其值为sessionId
        if (!StringUtils.isEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            // 请求头中无 Authorization 字段，从Cookie中获取sessionId
            return super.getSessionId(request, response);
        }
    }
}
