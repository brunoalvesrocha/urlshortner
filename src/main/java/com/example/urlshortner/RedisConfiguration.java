package com.example.urlshortner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

/**
 * @author bruno.alves.rocha
 */

@Configuration
public class RedisConfiguration {

    @Bean
    ReactiveRedisOperations<String, String> reactiveRedisOperations(
            ReactiveRedisConnectionFactory factory ) {
        return new ReactiveStringRedisTemplate(factory);
    }
}
