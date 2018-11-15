package com.lenovo.ecr.hybirdcloud.uds.facede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableJpaRepositories("com.lenovo.ecr.hybirdcloud.uds.db.mysql.repository")
@EnableMongoRepositories("com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository")
@EntityScan("com.lenovo.ecr.hybirdcloud.uds.db.mysql.entity")
public class HybirdcloudUdsFacedeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HybirdcloudUdsFacedeApplication.class, args);
	}
}
