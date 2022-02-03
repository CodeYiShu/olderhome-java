package com.codeshu.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author ShuCode
 * @date 2022/1/28 14:57
 * @Email 13828965090@163.com
 */
@Configuration
public class RedisConfig {
	@Bean
	public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<Object,Object> template = new RedisTemplate<>(); //创建RedisTemplate
		template.setConnectionFactory(redisConnectionFactory);  //将此连接工厂设置给RedisTemplate
		//创建JSON格式序列化对象，对缓存数据key和value进行转换
		Jackson2JsonRedisSerializer jsonSerial = new Jackson2JsonRedisSerializer(Object.class);
		//解决查询缓存转换异常的问题，创建ObjectMapper，然后将其设置给JSON格式序列化对象
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jsonSerial.setObjectMapper(om);
		//设置RedisTemplate的defaultSerializer为JSON序列化
		template.setDefaultSerializer(jsonSerial);
		return template;
	}
}
