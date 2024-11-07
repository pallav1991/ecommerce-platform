package com.ecommerce.gateway;

import com.ecommerce.jwtUtil.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@ComponentScan("com.ecommerce")
@EnableDiscoveryClient
public class GatewayApplication {

	@Value("${jwt.secret}")
	private String jwtSecret;

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	@Primary
	public JWTUtil jwtUtil() {
		return new JWTUtil(jwtSecret);
	}

}
