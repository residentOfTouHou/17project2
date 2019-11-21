/**
 *
 */
package com.cskaoyan.mall.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Component
public class CustomSessionManager extends DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest servletRequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String header = request.getHeader("X-Litemall-Admin-Token");
        String header1 = request.getHeader("X-Litemall-Token");
        if(header != null && !"".equals(header)){
            return header;
        }
        if(header1 != null && !"".equals(header1)){
            return header1;
        }

        return super.getSessionId(request, response);
    }
}
