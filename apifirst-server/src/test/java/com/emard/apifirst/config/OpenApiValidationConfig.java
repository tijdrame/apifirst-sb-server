package com.emard.apifirst.config;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiValidationConfig {
    @Bean
    public Filter validationFilter(){
        return new OpenApiValidationFilter(
false,//enable request validation
                false//enable response validation
        );
    }
    @Bean
    public WebMvcConfigurer openAPIValidationInterceptor(){
        String url = """
                https://api.redocly.com/registry/bundle/init_redocly/API%20First%20with%20Spring%20Boot%20-%20Developement/v1/openapi.yaml?branch=developement""";
        OpenApiInteractionValidator validator = OpenApiInteractionValidator.
                createForSpecificationUrl(url).build();
        OpenApiValidationInterceptor interceptor = new OpenApiValidationInterceptor(validator);
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(interceptor);
            }
        };
    }

}
