package com.apigateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apigateway.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {



	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder,JwtAuthenticationFilter filter) {
		return builder.routes().route("USER-SERVICE", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("http://localhost:3005"))
				.route("COMMENT-SERVICE", r -> r.path("/posts/{postId}/comments/**").filters(f -> f.filter(filter)).uri("http://localhost:3015"))
				.route("POST-SERVICE", r -> r.path("/posts/**").filters(f -> f.filter(filter)).uri("http://localhost:3010"))
				.route("LIKE-SERVICE", r -> r.path("/postsOrComments/{postOrCommentId}/**").filters(f -> f.filter(filter)).uri("http://localhost:3020"))
				.route("AUTHENTICATION-AUTHORIZATION-SERVICE", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("http://localhost:3000")).build();
	}

}
