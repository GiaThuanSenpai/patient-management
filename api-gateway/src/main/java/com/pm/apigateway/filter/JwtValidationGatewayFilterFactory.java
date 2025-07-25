package com.pm.apigateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

	private final WebClient webClient;

	public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder,
			@Value("${user.service.url}") String userServiceUrl) {
		this.webClient = webClientBuilder.baseUrl(userServiceUrl).build();
	}

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
			if (token == null || !token.startsWith("Bearer ")) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}

			return webClient.get().uri("/users/validate").header(HttpHeaders.AUTHORIZATION, token)
					.exchangeToMono(response -> {
						if (response.statusCode().is2xxSuccessful()) {
							return chain.filter(exchange); // Tiếp tục nếu xác thực thành công
						} else {
							exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
							return exchange.getResponse().setComplete();
						}
					}).onErrorResume(e -> {
						exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
						return exchange.getResponse().setComplete();
					});
		};
	}
}