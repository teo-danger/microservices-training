package com.dedagroup.msnamingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsNamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNamingServiceApplication.class, args);
	}

}
