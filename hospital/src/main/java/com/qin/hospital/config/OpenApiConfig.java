package com.qin.hospital.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: OpenApiConfig
 * @Author Qin
 * @Package com.qin.hospital.config
 * @Version 1.0
 * @Date 2025/2/5 17:23
 * @description:
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("com.qin.hospital.controller") // 例如 "com.example.demo.controller"
                .build();
    }
}
