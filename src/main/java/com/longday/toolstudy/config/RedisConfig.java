package com.longday.toolstudy.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.longday.toolstudy.utils.JacksonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置
 * @author 君
 */
@Configuration
public class RedisConfig {
 
    /**
     * 设置key跟value的序列化方式
     * @param factory RedisConnectionFactory
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper om = new ObjectMapper();
        boolean isSuccessful = JacksonUtils.configJacksonObjectMapper(om);
        //启用反序列化所需的类型信息,在属性中添加@class
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        if(!isSuccessful){
            throw new RuntimeException("redisTemplate ObjectMapper 配置异常 ");
        }
        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key序列化
        template.setKeySerializer(stringRedisSerializer);
        //value序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //Hash key序列化
        template.setHashKeySerializer(stringRedisSerializer);
        // Hash Value序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}