package com.zoro.springboot.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @date 2018/8/27  17:06
 * 通过继承WebMvcConfigurerAdapter注册拦截器
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 拦截规则
        //多个拦截器时 以此添加 执行顺序按添加顺序
        //注意路径配置：需要两个**号
        registry.addInterceptor(new CustomHandlerInterceptor()).addPathPatterns("/**");
    }

}
