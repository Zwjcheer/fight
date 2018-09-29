package com.baozun.config;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Configuration
//@ConditionalOnClass({ JedisCluster.class })
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    //    @Value("${spring.redis.cluster.nodes}")
    //    private String clusterNodes;
    //
    //    @Value("${spring.redis.timeout}")
    //    private int timeout;
    //
    //    @Value("${spring.redis.pool.max-idle}")
    //    private int maxIdle;
    //
    //    @Value("${spring.redis.pool.max-wait}")
    //    private long maxWaitMillis;
    //
    //    @Value("${spring.redis.commandTimeout}")
    //    private int commandTimeout;

    //    @Bean
    //    public JedisCluster getJedisCluster() {
    //
    //        String[] cNodes = clusterNodes.split(",");
    //        Set<HostAndPort> hashSet = new HashSet<HostAndPort>();
    //        for (String node : cNodes) {
    //            String[] hp = node.split(":");
    //            hashSet.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
    //        }
    //        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    //        jedisPoolConfig.setMaxIdle(maxIdle);
    //        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
    //        return new JedisCluster(hashSet, commandTimeout, jedisPoolConfig);
    //    }
    //
    //    /**
    //     * 
    //     * 设置数据存入redis 的序列化方式
    //     * redisTemplate序列化默认使用的jdkSerializeable,存储二进制字节码,导致key会出现乱码，所以自定义
    //     * *序列化类 *
    //     * * @paramredisConnectionFactory
    //     * 
    //     */
    //    @SuppressWarnings({ "unchecked", "rawtypes" })
    //    @Bean
    //    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    //
    //        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
    //        redisTemplate.setConnectionFactory(redisConnectionFactory);
    //        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
    //        ObjectMapper objectMapper = new ObjectMapper();
    //        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    //        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
    //        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    //        redisTemplate.setKeySerializer(new StringRedisSerializer());
    //        redisTemplate.afterPropertiesSet();
    //        return redisTemplate;
    //    }
    /**
     * 生成key的策略
     * 
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {

        return new KeyGenerator() {

            @Override
            public Object generate(Object arg0, Method arg1, Object...arg2) {

                StringBuilder sb = new StringBuilder();
                sb.append(arg0.getClass().getName());
                sb.append(arg1.getName());
                for (Object obj : arg2) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {

        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间(秒)
        rcm.setDefaultExpiration(600);
        return rcm;
    }

    /**
     * RedisTemplate配置
     */
    @SuppressWarnings("unchecked")
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

        StringRedisTemplate template = new StringRedisTemplate(factory);
        @SuppressWarnings("rawtypes")
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
