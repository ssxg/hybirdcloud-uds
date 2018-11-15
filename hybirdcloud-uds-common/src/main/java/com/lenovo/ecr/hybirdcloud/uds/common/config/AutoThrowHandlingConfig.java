package com.lenovo.ecr.hybirdcloud.uds.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import com.lenovo.ecr.hybirdcloud.uds.common.aspect.AutoThrowHandlingSpect;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AutoThrowHandlingConfig {
    @Bean
    public AutoThrowHandlingSpect autoThrowHandlingSpect()
    {
        return new AutoThrowHandlingSpect();
    }
}
