package com.zoro.springboot.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @date 2018/8/27  17:06
 * 通过继承WebMvcConfigurerAdapter注册拦截器
 */
@Configuration
//public class WebMvcConfigurer extends WebMvcConfigurerAdapter {已过时
/**
 * 继承WebMvcConfigurationSupport来代替 ，但是一旦继承WebMvcConfigurationSupport后就会出现新的问题，会发现Spring Boot的WebMvc自动配置失效，
 * 具体表现比如访问不到静态资源（js，css等）了，这是因为WebMvc的自动配置都在WebMvcAutoConfiguration类中，
 * 但是类中有这个注解@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})，意思是一旦在容器中检测到WebMvcConfigurationSupport这个类，
 * WebMvcAutoConfiguration类中的配置都不生效。所以一旦我们自己写的配置类继承了WebMvcConfigurationSupport，
 * 相当于容器中已经有了WebMvcConfigurationSupport，所以默认配置都不会生效，都得自己在配置文件中配置
 */
//public class WebMvcConfigurer extends WebMvcConfigurationSupport {
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 拦截规则
        //多个拦截器时 以此添加 执行顺序按添加顺序
        //注意路径配置：需要两个**号
        registry.addInterceptor(new CustomHandlerInterceptor()).addPathPatterns("/**");
    }

}
