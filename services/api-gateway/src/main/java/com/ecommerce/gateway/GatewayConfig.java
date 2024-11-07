package com.ecommerce.gateway;

import com.ecommerce.jwtUtil.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    @Autowired
    private JWTUtil jwtUtil;

    @Bean
    public GlobalFilter loginFilter() {
        return new GlobalFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String token = exchange.getRequest().getHeaders().getFirst("Authorization");
                if (token != null && !jwtUtil.isTokenExpired(token)) {
                    Claims claims = jwtUtil.extractAllClaims(token);
                    exchange.getRequest().mutate().header("username", claims.getSubject()).build();
                }

                return chain.filter(exchange);
            }
        };
    }
}
