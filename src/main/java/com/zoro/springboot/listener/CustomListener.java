package com.zoro.springboot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @date 2018/8/27  17:57
 */
@WebListener
public class CustomListener implements ServletRequestListener {

    private static final Logger logger = LoggerFactory.getLogger(CustomListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        logger.info("监听器：销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        logger.info("监听器：初始化");
    }
}
