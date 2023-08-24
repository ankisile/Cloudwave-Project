package com.cloudwave.cloudwavewebservice.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 메서드 허용
                .allowedMethods(
                        HttpMethod.POST.name(), HttpMethod.GET.name(),
                        HttpMethod.PUT.name(), HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name()
                )
//                // 요청 헤더 허용
//                .allowedHeaders("Authorization", ...)
//        // 응답 헤더 허용
//            .exposedHeaders("Authorization", ...)
        // 오리진 허용
            .allowedOrigins("*");

    }
}
