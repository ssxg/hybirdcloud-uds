package com.lenovo.ecr.hybirdcloud.uds.aggregate.controller;

import com.lenovo.ecr.hybirdcloud.uds.aggregate.service.MongoServerAggregationService;
import com.lenovo.ecr.hybirdcloud.uds.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ComponentScan("com.lenovo.ecr.hybirdcloud.uds.common.config")
@RestController
public class ServerController {
    @Autowired
    MongoServerAggregationService serverService;

    @GetMapping("/v1/server/aggregate")
    public String aggregate()
    {
        long timestamp = DateUtil.getZero();
        boolean result = serverService.createDailyServerAggregation(timestamp);
        return result?"succeed":"failed";
    }
}
