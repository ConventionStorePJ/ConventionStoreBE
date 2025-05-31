package com.convention_store.config;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final Environment env;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        boolean isDev = env.acceptsProfiles(Profiles.of("dev"));

        if (isDev) {
            // 개발 환경에서는 H2 콘솔과 iframe 허용
            http
                    .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
                    .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        }

        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/api/**",
                                "/h2-console/**" // ✅ H2 콘솔 허용
                        ).permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .build();
    }
    
    /**
     * 전체 CORS 허용을 위한 CorsConfigurationSource Bean 정의
     * - 개발/테스트 환경에서만 사용하고, 운영 환경에서는 구체적인 Origin, Method, Header를 명시해야 합니다.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 1. 모든 Origin 허용
        //    **주의: allowCredentials(true)와 함께 '*'를 사용할 수 없습니다.**
        //    만약 allowCredentials(true)를 사용한다면, 구체적인 Origin(예: "http://localhost:3000")을 명시해야 합니다.
        configuration.setAllowedOrigins(List.of("*"));
        
        // 2. 모든 HTTP Method 허용 (GET, POST, PUT, DELETE, OPTIONS 등)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        
        // 3. 모든 Header 허용
        configuration.setAllowedHeaders(List.of("*"));
        
        // 4. 자격 증명(쿠키, HTTP 인증 등) 허용 여부
        //    true로 설정하면, 실제 요청에 쿠키나 인증 정보가 포함될 수 있습니다.
        //    **'allowCredentials(true)'를 사용하려면 'allowedOrigins'가 '*'가 아닌 구체적인 Origin이어야 합니다.**
        //    운영 환경에서는 신중하게 고려해야 합니다.
        configuration.setAllowCredentials(true);
        
        // 5. pre-flight 요청(OPTIONS)의 캐싱 시간 (초 단위)
        configuration.setMaxAge(3600L); // 1시간
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 모든 경로(/**)에 대해 위에서 정의한 CORS 설정을 적용
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
