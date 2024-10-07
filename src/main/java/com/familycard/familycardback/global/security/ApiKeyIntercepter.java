package com.familycard.familycardback.global.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyIntercepter implements HandlerInterceptor {
    private static final String API_KEY_HEADER = "X-Api-Key";
    @Value("${familyCard.valid.key}")
    private String VALID_API_KEY;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader(API_KEY_HEADER);

        if (VALID_API_KEY.equals(apiKey)) {
            return true; // 키가 유효하면 요청을 계속 진행
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Invalid API Key\"}");
            return false; // 키가 유효하지 않으면 요청을 중단
        }
    }
}
