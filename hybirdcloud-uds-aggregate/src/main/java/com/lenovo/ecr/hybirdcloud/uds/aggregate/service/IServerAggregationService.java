package com.lenovo.ecr.hybirdcloud.uds.aggregate.service;

import com.lenovo.ecr.hybirdcloud.uds.aggregate.model.ServerAggregation;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyServerAggregation;

import java.util.List;

public interface IServerAggregationService {
    List<ServerAggregation> aggregate(long timestamp);
    DailyServerAggregation parseAggregations(List<ServerAggregation> aggregations);
    boolean saveDailyServerAggregation(DailyServerAggregation dailyServerAggregation);
}
