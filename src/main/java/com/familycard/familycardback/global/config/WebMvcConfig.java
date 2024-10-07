package com.familycard.familycardback.global.config;

import com.familycard.familycardback.global.security.ApiKeyIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //수동으로 스프링 컨테이너에 Bean 등록하는 방법
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(
            ViewResolverRegistry registry
    )
    {
        MustacheViewResolver resolver =
                new MustacheViewResolver();
        resolver.setCharset("UTF-8");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        registry.viewResolver(resolver);
    }

    @Autowired
    private ApiKeyIntercepter apiKeyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyInterceptor)
                .addPathPatterns("/**") // 모든 경로에 인터셉터 적용
                .excludePathPatterns("/health"); // 상태체크는 예외
    }
}