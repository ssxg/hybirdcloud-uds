package com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.aggregation;

import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyServerAggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DailyServerAggregationRepository extends MongoRepository<DailyServerAggregation,String> {
    List<DailyServerAggregation> findByTimestamp(long timestamp);
}
