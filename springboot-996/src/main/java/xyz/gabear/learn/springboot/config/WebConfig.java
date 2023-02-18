package xyz.gabear.learn.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.gabear.learn.springboot.interceptor.RateLimitInterceptor;

import javax.annotation.Resource;

/**
 * 类名称：WebConfig
 * ********************************
 * <p>
 * 类描述：Web配置类
 *
 * @author
 * @date 下午9:34
 */
@Configuration
@EnableWebMvc
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    /**
     * 全局限流拦截器
     */
    @Resource
    private RateLimitInterceptor rateLimitInterceptor;

    /**
     * 向Web中添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 配置限流拦截器，拦截所有以/api/开头的请求
        registry.addInterceptor(rateLimitInterceptor).addPathPatterns("/api/**");
    }

    /**
     * 静态资源配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 配置本地文件夹目录映射
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("");

        // Swagger2做的映射
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
