package com.lenovo.ecr.hybirdcloud.uds.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HybirdcloudUdsRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HybirdcloudUdsRegisterApplication.class, args);
	}
}
