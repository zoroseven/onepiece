package com.zoro.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gaocheng
 * @date 2018/8/30  22:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisOpsTest {

	@Resource(name = "redisTemplate")
	private RedisTemplate redisTemplate;

	@Test
	public void setOpsTest(){
//		String[] strarrays = new String[]{"strarr3","sgtarr2"};
//		System.out.println(redisTemplate.opsForSet().add("set",strarrays));
//		Set<String> set = redisTemplate.opsForSet().members("set");
//		System.out.println(set.contains("strarr1"));
//		System.out.println(set.contains("strarr"));
		System.out.println(redisTemplate.opsForSet().isMember("set","strarr1"));//true
		System.out.println(redisTemplate.opsForSet().isMember("set","strarr"));//false
		ConcurrentHashMap map = new ConcurrentHashMap();
	}
}
