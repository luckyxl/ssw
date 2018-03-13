package com.aas.ssw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * redis配置类
 *
 * @author xl
 */
@Configuration
@EnableCaching
@ConditionalOnProperty(name = "redis.enabled")
public class RedisConfig extends CachingConfigurerSupport {


    @Value("${spring.redis.host:''}")
    private String hostName;
    @Value("${spring.redis.port:''}")
    private String port;
    @Value("${spring.redis.password:''}")
    private String password;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    /**
     * 缓存管理器
     *
     * @return CacheManager
     */
    @Override
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory);
        Set<String> cacheNames = new HashSet<String>() {{
            add("codeNameCache");
        }};
        builder.initialCacheNames(cacheNames);
        return builder.build();

    }

    /**
     * @return 自定义策略生成的key
     * @description 自定义的缓存key的生成策略 </br>
     * 若想使用这个key</br>
     * 只需要将注解上keyGenerator的值设置为customKeyGenerator即可</br>
     */
    @Bean
    public KeyGenerator customKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }


}
