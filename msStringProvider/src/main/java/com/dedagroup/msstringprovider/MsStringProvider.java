package com.dedagroup.msstringprovider;

import com.dedagroup.msstringprovider.feignClient.TransformerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients(clients = TransformerClient.class)
public class MsStringProvider {

	public static void main(String[] args) {
		SpringApplication.run(MsStringProvider.class, args);
	}

}
