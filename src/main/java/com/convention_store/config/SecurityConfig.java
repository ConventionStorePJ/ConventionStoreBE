package com.convention_store.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final Environment env;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        boolean isDevProfileActive = env.acceptsProfiles(Profiles.of("dev"));
        if (isDevProfileActive) {
            // 개발 환경에서는 X-Frame-Options 비활성화
            http.headers(headers ->
                headers.frameOptions(FrameOptionsConfig::disable)
            );
        }
        
        return http
                .csrf(AbstractHttpConfigurer::disable) // 🔓 CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll() // 🔓 Swagger 허용
                        .anyRequest().permitAll() // 🔓 나머지도 지금은 전체 허용
                )
                .formLogin(AbstractHttpConfigurer::disable) // 🔒 기본 로그인 폼 비활성화
                .build();
    }
}
