package com.lenovo.ecr.hybirdcloud.uds.facede.controller;

import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.raw.ServerRepository;
import com.lenovo.ecr.hybirdcloud.uds.facede.enums.AggregationDimentionEnum;
import com.lenovo.ecr.hybirdcloud.uds.facede.model.viewModel.DailyServerAggregationVM;
import com.lenovo.ecr.hybirdcloud.uds.facede.service.IServerService;
import com.mongodb.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@ComponentScan("com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository")
public class ServerController {

    @Autowired
    ServerRepository serverRepository;

    @Autowired
    IServerService serverService;

    @GetMapping("/v1/server/aggregation")
    public DailyServerAggregationVM aggregation(String dimention)
    {
        if(dimention!=null&&!dimention.isEmpty()) {
            AggregationDimentionEnum aggregationDimentionEnum = AggregationDimentionEnum.ALL;
            try {
                aggregationDimentionEnum = Enum.valueOf(AggregationDimentionEnum.class, dimention);
            }catch (Exception ex){

            }

            return serverService.getLastServerAggregation(aggregationDimentionEnum);
        }
        else{
            return serverService.getLastServerAggregation();
        }
    }
}
