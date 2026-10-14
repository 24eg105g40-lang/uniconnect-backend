package com.educonnect.educonnectbackend.config;
import org.springframework.context.annotation.*;
import  org.springframework.web.servlet.config.annotation.*;
@Configuration      //tells springboot that this is a configuration class
public class CorsConfig {
    @Bean            // creates and registers this configuration as a spring bean
    public WebMvcConfigurer corsConfigurer() { //webMVCconfiguration allows us to customize spring mvc settings
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //allow CORS rules to all endpoints starting with /api/
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}