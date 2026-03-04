package com.finapp.finance_calculator.tnc;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ConsentInterceptor consentInterceptor;
    
    public WebConfig(ConsentInterceptor consentInterceptor) {
		this.consentInterceptor = consentInterceptor;
	}
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(consentInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/consent/**",
                        "/api/bikes/companies",
                        "/api/bikes/models/**",
                        "/api/bikes/variants/**"
                );
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081")
                .allowCredentials(true)
                .allowedMethods("*");
    }
}