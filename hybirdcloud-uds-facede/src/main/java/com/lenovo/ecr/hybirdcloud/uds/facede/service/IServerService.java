package com.lenovo.ecr.hybirdcloud.uds.facede.service;

import com.lenovo.ecr.hybirdcloud.uds.facede.enums.AggregationDimentionEnum;
import com.lenovo.ecr.hybirdcloud.uds.facede.model.viewModel.DailyServerAggregationVM;

public interface IServerService {
    DailyServerAggregationVM getLastServerAggregation();
    DailyServerAggregationVM getLastServerAggregation(AggregationDimentionEnum dimention);

}
