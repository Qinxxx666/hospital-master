package com.qin.hospital.config;

import com.qin.hospital.filter.WebRequestInterception;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author WanYue
 * 解决跨域问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    /**
     * 初始化拦截器，防止redisTemplate为null
     *
     * @return WebRequestInterception
     */
    @Bean
    public WebRequestInterception getWebRequestInterception() {
        return new WebRequestInterception();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}) // 支持方法
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

    /**
     * 请求拦截器 拦截除登录注册之外的所有请求
     *
     * @param registry 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getWebRequestInterception())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register");
    }
}
