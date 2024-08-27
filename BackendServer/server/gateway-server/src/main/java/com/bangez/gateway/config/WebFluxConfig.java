package com.bangez.gateway.config;

import com.bangez.gateway.filter.HttpsToHttpRedirectFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@Configuration
public class WebFluxConfig {

    private final HttpsToHttpRedirectFilter httpsToHttpRedirectFilter;

    public WebFluxConfig(HttpsToHttpRedirectFilter httpsToHttpRedirectFilter) {
        this.httpsToHttpRedirectFilter = httpsToHttpRedirectFilter;
    }

    @Bean
    public WebFilter customWebFilter() {
        return httpsToHttpRedirectFilter;
    }
}
