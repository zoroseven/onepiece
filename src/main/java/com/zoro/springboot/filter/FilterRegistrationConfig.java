package com.zoro.springboot.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2018/8/27  17:41
 */
@Configuration
public class FilterRegistrationConfig {

    @Bean
    public CustomFilter customFilter() {
        return new CustomFilter();
    }

    @Bean
    public FilterRegistrationBean filterProxy() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //DelegatingFilterProxy httpBasicFilter = new DelegatingFilterProxy();
        registrationBean.setFilter(customFilter());
        //Map<String, String> m = new HashMap<String, String>();
        //m.put("targetBeanName", "customFilter");
        //m.put("targetFilterLifecycle", "true");
        //registrationBean.setInitParameters(m);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
