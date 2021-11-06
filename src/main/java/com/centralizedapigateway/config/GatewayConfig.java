package com.centralizedapigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.centralizedapigateway.filter.JwtAuthenticationFilter;


@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		
		// Use uri("lb://APPLICATION-NAME") on production. It is not working in current system so used http://localhost
		return builder.routes().route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("http://localhost:8890"))
				.route("users", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("http://localhost:8888/")).build();
			
	}

}
