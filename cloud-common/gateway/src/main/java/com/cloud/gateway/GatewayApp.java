package com.cloud.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * <p>
 *
 * </p>
 *
 * @Title GatewayApp.java
 * @Package com.cloud.gateway
 * @Author <a href="mailto:tuanyu@sinotn.com">au .T</a>
 * @Date 2019/3/30 17:28
 */
@RefreshScope//刷新配置
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }

//    @Bean
//    @Order(-1)
//    public GlobalFilter a() {
//        return (exchange, chain) -> {
//            System.err.println("1---pre");
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                System.err.println("6---post");
//            }));
//        };
//    }
//
//    @Bean
//    @Order(0)
//    public GlobalFilter b() {
//        return (exchange, chain) -> {
//
//            System.err.println("2---pre");
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                System.err.println("5---post");
//            }));
//        };
//    }
//
//    @Bean
//    @Order(1)
//    public GlobalFilter c() {
//        return (exchange, chain) -> {
//
//            System.err.println("3---pre");
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//
//                System.err.println("4---post");
//            }));
//        };
//    }


}
