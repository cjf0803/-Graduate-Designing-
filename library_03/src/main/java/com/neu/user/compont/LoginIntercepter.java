package com.neu.user.compont;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 陈锦房
 */
public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username=(String) request.getSession().getAttribute("username");
        if (username==null){
            System.out.println("拦截器运行");
            //重定向到login.vue中
            response.sendRedirect("http://localhost:8080");
            return  false;
        }else{
            return true;
        }



    }


}
