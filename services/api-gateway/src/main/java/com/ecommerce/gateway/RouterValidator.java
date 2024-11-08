package com.ecommerce.gateway;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

    public static final List<String> openEndpoints = List.of(
            "/auth/register",
            "/auth/login"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints.stream()
                    .noneMatch(uri -> isContains(request, uri));

    private static boolean isContains(ServerHttpRequest request, String uri) {
        return request.getURI().getPath().contains(uri);
    }
}