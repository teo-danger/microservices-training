package com.dedagroup.springboottraining_resttemplate;

import com.dedagroup.springboottraining_resttemplate.controller.OpenFeignController;
import com.dedagroup.springboottraining_resttemplate.controller.WebClientController;
import com.dedagroup.springboottraining_resttemplate.feignClient.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients(clients = UserClient.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

}
