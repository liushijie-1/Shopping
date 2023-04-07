package com.lsj.config;

import com.lsj.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/*
* 处理器拦截器的注册
* */
//@Configuration//加载当前拦截器
public class LoginInterceptorConfig implements WebMvcConfigurer {

    /*配置拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义的拦截器对象
        HandlerInterceptor interceptor =new LoginInterceptor();
        //配置白名单，存放在集合中
        List<String> list =new ArrayList<>();
        list.add("/bootstrap3/**");
        list.add("/css/**");
        list.add("/images/**");
        list.add("/js/**");
        list.add("/web/register.html");
        list.add("/web/login.html");
        list.add("/web/index.html");
        list.add("/web/product.html");
        list.add("/users/reg");
        list.add("/users/login");
        list.add("/districts/**");
        list.add("/web/index.html");
        list.add("/products/**");

        //完成拦截器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")//表示要拦截的URL是什么
                .excludePathPatterns(list);
    }
}
