package com.bangez.gateway.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
@Component
public class HttpsToHttpRedirectFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Check if the request is HTTPS
        if (exchange.getRequest().getURI().getScheme().equals("https")) {
            // Create HTTP URI
            URI uri = exchange.getRequest().getURI();
            URI httpUri = URI.create("http://" + uri.getHost() + ":" + 8443 + uri.getRawPath());
            if (uri.getQuery() != null) {
                httpUri = URI.create(httpUri.toString() + "?" + uri.getQuery());
            }

            // Redirect to HTTP
            exchange.getResponse().setStatusCode(HttpStatus.FOUND);
            exchange.getResponse().getHeaders().setLocation(httpUri);

            return exchange.getResponse().setComplete(); // Ensure response is completed
        }

        // Continue with the filter chain
        return chain.filter(exchange);
    }
}
