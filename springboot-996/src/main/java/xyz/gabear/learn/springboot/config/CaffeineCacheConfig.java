package xyz.gabear.learn.springboot.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 类名称：CaffeineCacheConfig
 * ********************************
 * <p>
 * 类描述：缓存管理器配置类
 *
 * @author
 * @date 下午10:35
 */
@Configuration
@EnableCaching
@Slf4j
public class CaffeineCacheConfig {

    /**
     * CacheManager实现类
     *
     * @return
     */
    @Bean("cacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        // 缓存集合
        ArrayList<CaffeineCache> caches = new ArrayList<>();

        // 对缓存key属性做设置
        caches.add(new CaffeineCache("users-cache",
                Caffeine.newBuilder()

                        // 指定Key下的最大缓存数据量
                        .maximumSize(1000)

                        // 最后一次访问之后 120秒 过期
                        .expireAfterAccess(120, TimeUnit.SECONDS)

                        .build()));

        cacheManager.setCaches(caches);

        return cacheManager;
    }

}
