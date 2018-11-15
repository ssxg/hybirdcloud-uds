package com.lenovo.ecr.hybirdcloud.uds.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class HybirdcloudUdsConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(HybirdcloudUdsConfigApplication.class, args);
	}
}
