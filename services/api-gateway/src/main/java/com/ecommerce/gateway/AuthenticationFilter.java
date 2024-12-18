package com.ecommerce.gateway;

import com.ecommerce.jwtUtil.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouterValidator validator;

    @Autowired
    private JWTUtil jwtUtils;

    public AuthenticationFilter() {
        super(AuthenticationFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            //header contains token or not
            if (!validator.isSecured.test(exchange.getRequest())) {
                return chain.filter(exchange);
            }
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("missing authorization header");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
            }
            try {
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                if ((isTokenValid(authHeader))) {
                    return chain.filter(exchange);
                }

            } catch (Exception e) {
                System.out.println("invalid access...!");
                throw new RuntimeException("un authorized access to application");
            }
            return chain.filter(exchange);
        });
    }

    private boolean isTokenValid(String token) {

        RestTemplate restTemplate = new RestTemplate();
        String authServiceUrl = "http://auth-service/validate"; // Replace with your auth service URL

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setBearerAuth(token);
        HttpEntity<String> requestEntity = new HttpEntity<>(token, headers);

        try {
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    authServiceUrl,
                    HttpMethod.POST,
                    requestEntity,
                    Boolean.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Boolean isValid = response.getBody();
                return isValid != null && isValid;
            } else {
                // Handle non-2xx status codes (e.g., log the error)
                System.err.println("Authentication service returned non-2xx status code: " + response.getStatusCode());
                return false;
            }
        } catch (Exception ex) {
            // Handle exceptions (e.g., connection errors)
            System.err.println("Error calling authentication service: " + ex.getMessage());
            return false;
        }
    }

    public static class Config {

    }
}