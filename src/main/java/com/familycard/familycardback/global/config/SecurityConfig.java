package com.familycard.familycardback.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration  // 얘가 있어야 중요한 컴포넌트임을 알 수 있음.
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${familyCard.server.domain}")
    private String serverDomain;

    @Value("${familyCard.client.domain}")
    private String clientDomain;

    @Value("${familyCard.server.domain1}")
    private String serverDomain2;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .formLogin((form) -> form.disable())
                .httpBasic((basic) -> basic.disable())
                .cors((cors) -> cors.configurationSource(corsConfigurationSource())) // Single configuration source
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/test", "/swagger-ui/**", "/v3/api-docs/**", "/api/login", "/api/register", "/error", "/api/health", "/actuator/health").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                        .anyRequest().authenticated()
                ).requiresChannel((channel) -> channel.anyRequest().requiresSecure()); // HTTP 요청을 HTTPS로 리디렉션

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:3000"); // 허용할 도메인 추가
//        configuration.addAllowedOrigin("http://localhost:3001");
//        configuration.addAllowedOrigin(serverDomain); // 환경 변수로 받은 도메인 추가
//        configuration.addAllowedOrigin(clientDomain); // 환경 변수로 받은 도메인 추가
//        configuration.addAllowedOrigin(serverDomain2);
//        configuration.addAllowedMethod("*"); // 모든 메서드 허용
//        configuration.addAllowedHeader("*"); // 모든 헤더 허용
//        configuration.setAllowCredentials(true); // 자격 증명 허용
//        configuration.setMaxAge(3600L); // 사전 요청 결과를 1시간 동안 캐시
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;

        configuration.addAllowedOriginPattern("*"); // 모든 도메인 허용
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
