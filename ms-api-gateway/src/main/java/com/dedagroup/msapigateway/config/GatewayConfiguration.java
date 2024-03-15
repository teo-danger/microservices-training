package com.dedagroup.msapigateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;


@org.springframework.context.annotation.Configuration
public class GatewayConfiguration implements GlobalFilter {

    private final Logger log = LoggerFactory.getLogger(GatewayConfiguration.class);

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("http-bin", r->r.path("/get")
                        .filters(f->f
                                .addRequestParameter("req-param", "ciao")
                                .addRequestHeader("X-head", "test"))
                        .uri("http://httpbin.org/get"))
                .route("transform", r->r.path("/transform/**")
                        .uri("lb://ms-string-transformer"))

                .route("provide", r-> r.path("/string/**", "/string-provider/**")
                        .uri("lb://ms-string-provider"))

                .route("provide-new", r-> r.path("/string-provider-new/**")
                        .filters(f->f.rewritePath("/string-provider-new/(?<segment>.*)", "/string-provider/${segment}"))
                    .uri("lb://ms-string-provider"))
                .build();
    }


    @Bean
    public GlobalFilter customFilter() {
        return new GatewayConfiguration();
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final String requestURI = exchange.getRequest().getURI().toASCIIString();
        final String method = exchange.getRequest().getMethod().toString();
        final int statusCode = Objects.requireNonNull(exchange.getResponse().getStatusCode()).value();
        log.info("request: " + requestURI + ", method: " + method + ", statusCode " + statusCode);

        return chain.filter(exchange);
    }
}
