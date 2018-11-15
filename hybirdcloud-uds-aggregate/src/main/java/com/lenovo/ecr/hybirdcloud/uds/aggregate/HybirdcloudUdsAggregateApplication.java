package com.lenovo.ecr.hybirdcloud.uds.aggregate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableMongoRepositories("com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository")
@EnableFeignClients
public class HybirdcloudUdsAggregateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HybirdcloudUdsAggregateApplication.class, args);
	}
}
