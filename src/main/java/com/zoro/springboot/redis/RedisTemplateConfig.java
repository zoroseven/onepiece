package com.zoro.springboot.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author gaocheng
 * @date 2018/8/20  22:43
 */
@Configuration
public class RedisTemplateConfig {

	@Autowired
	@Qualifier("logRedisTemplate")
	private RedisTemplate logRedisTemplate;
//
//	@Bean
//	public RedisTemplate redisTemplateInit(){
//		//解决设置redis值时候的乱码问题
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//		return redisTemplate;
//	}



	@PostConstruct
	public void init(){
		RedisAppender.bindRedis(logRedisTemplate);
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(
			@Value("${spring.redis.host}") String hostName,
			@Value("${spring.redis.port}") int port,
			@Value("${spring.redis.password}") String password,
			@Value("${spring.redis.jedis.pool.max-idle}") int maxIdle,
			@Value("${spring.redis.jedis.pool.max-active}") int maxTotal,
			@Value("${spring.redis.database}") int index,
			@Value("${spring.redis.jedis-factory.max-wait}") long maxWaitMillis) {
		//设置序列化
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		RedisTemplate<String, Object> temple = new RedisTemplate<String, Object>();
		temple.setConnectionFactory(this.connectionFactory(
				hostName, port, password, maxIdle, maxTotal, index, maxWaitMillis));
		RedisSerializer stringSerializer = new StringRedisSerializer();
		temple.setKeySerializer(stringSerializer);//key序列化
		temple.setValueSerializer(jackson2JsonRedisSerializer);//value序列化
		temple.setHashKeySerializer(stringSerializer);//Hash key序列化
		temple.setHashValueSerializer(jackson2JsonRedisSerializer);//Hash value序列化
		return temple;
	}

	@Bean(name = "logRedisTemplate")
	public RedisTemplate<String, Object> logRedisTemplate(
			@Value("${spring.redis.host}") String hostName,
			@Value("${spring.redis.port}") int port,
			@Value("${spring.redis.password}") String password,
			@Value("${spring.redis.jedis.pool.max-idle}") int maxIdle,
			@Value("${spring.redis.jedis.pool.max-active}") int maxTotal,
			@Value("${log.redis.key.database}") int index,
			@Value("${spring.redis.jedis-factory.max-wait}") long maxWaitMillis) {
		//设置序列化
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		RedisTemplate<String, Object> temple = new RedisTemplate<String, Object>();
		temple.setConnectionFactory(this.connectionFactory(
				hostName, port, password, maxIdle, maxTotal, index, maxWaitMillis));
		RedisSerializer stringSerializer = new StringRedisSerializer();
		temple.setKeySerializer(stringSerializer);//key序列化
		temple.setValueSerializer(jackson2JsonRedisSerializer);//value序列化
		temple.setHashKeySerializer(stringSerializer);//Hash key序列化
		temple.setHashValueSerializer(jackson2JsonRedisSerializer);//Hash value序列化
		return temple;
	}

	public RedisConnectionFactory connectionFactory(
			String redisHost, int redisPort, String redisAuth, int maxIdle, int maxTotal, int redisDb, long maxWait) {
		//已过时
		//JedisConnectionFactory jedis = new JedisConnectionFactory();
		//jedis.setHostName(hostName);
		//jedis.setPort(port);
		//jedis.setPassword(password);
		//jedis.setDatabase(index);
		//jedis.setPoolConfig(this.poolCofig(maxIdle, maxTotal, maxWaitMillis));
		//// 初始化连接pool
		//jedis.afterPropertiesSet();
		//RedisConnectionFactory factory = jedis;
		//return factory;
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxWaitMillis(maxWait);
		//poolConfig.setMinIdle(minIdle);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(false);
		poolConfig.setTestWhileIdle(true);

		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).and().readTimeout(Duration.ofMillis(30000)).build();
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setDatabase(redisDb);
		redisStandaloneConfiguration.setPort(redisPort);
		redisStandaloneConfiguration.setPassword(RedisPassword.of(redisAuth));
		redisStandaloneConfiguration.setHostName(redisHost);
		return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
	}

	public JedisPoolConfig poolCofig(int maxIdle, int maxTotal, long maxWaitMillis) {
		JedisPoolConfig poolCofig = new JedisPoolConfig();
		poolCofig.setMaxIdle(maxIdle);
		poolCofig.setMaxTotal(maxTotal);
		poolCofig.setMaxWaitMillis(maxWaitMillis);
		poolCofig.setTestOnBorrow(false);
		return poolCofig;
	}
}
