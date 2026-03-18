package org.example.parking_management_system.config;

import org.example.parking_management_system.interceptors.LoginInterception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterception loginInterception;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterception)
                .excludePathPatterns(
                        "/api/v1/admin/login",
                        "/api/v1/user/register",
                        "/api/v1/user/login");
    }
}
