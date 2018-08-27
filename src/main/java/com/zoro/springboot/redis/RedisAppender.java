package com.zoro.springboot.redis;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.filter.Filter;
import net.logstash.logback.layout.LoggingEventCompositeJsonLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Field;

public class RedisAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	private static RedisTemplate logRedisTemplate;
	public static void bindRedis(RedisTemplate logRedisTemplate){
		RedisAppender.logRedisTemplate=logRedisTemplate;
	}
	public static String service;
	private final static Logger logger = LoggerFactory.getLogger(RedisAppender.class);
	Layout<ILoggingEvent> layout;

	public void setFilter(Filter<ILoggingEvent> filter) {
		if(filter != null){
			this.addFilter(filter);
		}
	}
	
	String key = null;

	public RedisAppender() {
		layout = new LoggingEventCompositeJsonLayout();
	}

	@Override
	protected void append(ILoggingEvent event) {
		try {
			IThrowableProxy ithrowableProxy = event.getThrowableProxy();
			if(ithrowableProxy != null){
				LoggingEvent loggingEvent = (LoggingEvent)event;
				StringBuffer error = new StringBuffer();
				error.append(loggingEvent.getFormattedMessage())
					.append("\\r\\n")
					.append(ithrowableProxy.getClassName())
					.append(":")
					.append(ithrowableProxy.getMessage())
					.append("\\r\\n");
				StackTraceElementProxy[] array = ithrowableProxy.getStackTraceElementProxyArray();
				for (StackTraceElementProxy proxy : array) {
					error.append(proxy.getSTEAsString())
					.append("\\r\\n");
				}
				//利用反射改变原有的错误信息堆栈
				Class<? extends LoggingEvent> clazz = loggingEvent.getClass();
				Field throwableProxy = clazz.getDeclaredField("throwableProxy");
				throwableProxy.setAccessible(true);
				throwableProxy.set(loggingEvent, null);
				Field formattedMessage = clazz.getDeclaredField("formattedMessage");
				formattedMessage.setAccessible(true);
				formattedMessage.set(loggingEvent, error.toString());
				Field message = clazz.getDeclaredField("message");
				message.setAccessible(true);
				message.set(loggingEvent, error.toString());
			}
			String json = layout.doLayout(event);
			if(json != null){
				json = json.replaceAll("\t", "");
			}
			if(logRedisTemplate!=null){
				logRedisTemplate.boundListOps(key).rightPush(json);
			}
		} catch (Exception e) {
			logger.error("Log error:{}",e.getMessage(),e);
		}
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		String prefix = (System.getenv("APPLICATION_ENV") == null)?"dev":System.getenv("APPLICATION_ENV");
		key = prefix + "-" + key;
		this.key = key;
	}

	public Layout<ILoggingEvent> getLayout() {
		return layout;
	}

	public void setLayout(Layout<ILoggingEvent> layout) {
		this.layout = layout;
	}
}
