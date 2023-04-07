package com.lsj.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 定义一个拦截器
* */
public class LoginInterceptor implements HandlerInterceptor {

    /*
    * 获取session对象
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("uid");
        if (obj==null){//用户未登录系统，则重定向到login.html
            response.sendRedirect("/web/login.html");
            //结束后续调用
            return false;
        }
        //请求放行
        return true;
    }
}
