package com.cloud.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *
 * </p>
 *
 * @Title WebClientBuilder.java
 * @Package com.cloud.web.config
 * @Author <a href="mailto:au.t@foxmail.com">au .T</a>
 * @Date 2019/4/28 20:59
 */
public class WebClientBuilder {
    //web 的负载均衡
    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @LoadBalanced
    public org.springframework.web.client.RestTemplate restTemplate() {
        return builder.build();
    }
    //webflux 的负载均衡
//    @Bean
//    @LoadBalanced
//    public org.springframework.web.reactive.function.client.WebClient.Builder webBuilder() {
//        return org.springframework.web.reactive.function.client.WebClient.builder();
//    }
}
