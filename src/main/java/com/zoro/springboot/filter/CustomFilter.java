package com.zoro.springboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * @date 2018/8/27  17:26
 */
@WebFilter(filterName = "customFilter",urlPatterns = {"/*"})
public class CustomFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter 初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.info("doFilter 请求处理");
        //对request、response进行一些预处理
        // 比如设置请求编码
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
        //TODO 进行业务逻辑

        //链路 直接传给下一个过滤器
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("filter 销毁");
    }
}
