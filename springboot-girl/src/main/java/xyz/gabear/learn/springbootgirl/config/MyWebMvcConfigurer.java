package xyz.gabear.learn.springbootgirl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.gabear.learn.springbootgirl.config.interceptor.OneInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    // 添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器按照顺序执行，可以添加多个拦截路径
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/**");
    }

}