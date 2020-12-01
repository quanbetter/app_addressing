package com.quan.appservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateAutoConfig {
    @Bean
    public RestTemplate initRestTemplate() {
        return new RestTemplate();
    }
}
