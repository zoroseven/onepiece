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

	@Value("${spring.redis.host}")
	private String hostName;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.password}")
	private String password;
	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;
	@Value("${spring.redis.jedis.pool.min-idle}")
	private int minIdle;
	@Value("${spring.redis.jedis.pool.max-active}")
	private int maxTotal;
	@Value("${spring.redis.database}")
	private int database;
	@Value("${log.redis.database}")
	private int logDatabase;
	@Value("${spring.redis.jedis-factory.max-wait}")
	private long maxWaitMillis;

	@Autowired
	@Qualifier("logRedisTemplate")
	private RedisTemplate logRedisTemplate;

	@PostConstruct
	public void init(){
		RedisAppender.bindRedis(logRedisTemplate);
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> getRedisTemplate(){
		return redisTemplate(database);
	}

	@Bean(name = "logRedisTemplate")
	public RedisTemplate<String, Object> getLogRedisTemplate(){
		return redisTemplate(logDatabase);
	}

	public RedisTemplate<String, Object> redisTemplate(int redisDB) {
		//设置序列化
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		RedisTemplate<String, Object> temple = new RedisTemplate<>();
		temple.setConnectionFactory(this.connectionFactory(redisDB));
		RedisSerializer stringSerializer = new StringRedisSerializer();
		//key序列化
		temple.setKeySerializer(stringSerializer);
		//value序列化
		temple.setValueSerializer(jackson2JsonRedisSerializer);
		//Hash key序列化
		temple.setHashKeySerializer(stringSerializer);
		//Hash value序列化
		temple.setHashValueSerializer(jackson2JsonRedisSerializer);
		return temple;
	}

	public RedisConnectionFactory connectionFactory(int redisDb) {
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
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(false);
		poolConfig.setTestWhileIdle(true);

		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).and().readTimeout(Duration.ofMillis(30000)).build();
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setDatabase(redisDb);
		redisStandaloneConfiguration.setPort(port);
		redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		redisStandaloneConfiguration.setHostName(hostName);
		return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
	}

	/**
	 * 已过时
	 * @param maxIdle
	 * @param maxTotal
	 * @param maxWaitMillis
	 * @return
	 */
	@Deprecated
	public JedisPoolConfig poolCofig(int maxIdle, int maxTotal, long maxWaitMillis) {
		JedisPoolConfig poolCofig = new JedisPoolConfig();
		poolCofig.setMaxIdle(maxIdle);
		poolCofig.setMaxTotal(maxTotal);
		poolCofig.setMaxWaitMillis(maxWaitMillis);
		poolCofig.setTestOnBorrow(false);
		return poolCofig;
	}
}
