package com.convention_store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
public class H2ConsoleSecurityConfig {
    
    @Bean
    public SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
        // 중요한 점: "/h2-console/**" 경로에 대해서만 CSRF를 비활성화
        http.securityMatcher("/h2-console/**");
        
        // H2 콘솔 경로에 대한 CSRF 비활성화
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        
        // H2 콘솔의 iframe 사용을 허용하기 위해 X-Frame-Options 헤더를 비활성화
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        
        // H2 콘솔 경로에 대한 접근을 모두 허용
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console/**").permitAll());
        
        return http.build();
    }
}