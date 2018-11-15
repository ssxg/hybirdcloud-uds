package com.lenovo.ecr.hybirdcloud.uds.facede.service;

import com.lenovo.ecr.hybirdcloud.uds.common.util.DateUtil;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyServerAggregation;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.aggregation.DailyServerAggregationRepository;
import com.lenovo.ecr.hybirdcloud.uds.common.util.EmptyUtil;
import com.lenovo.ecr.hybirdcloud.uds.facede.enums.AggregationDimentionEnum;
import com.lenovo.ecr.hybirdcloud.uds.facede.model.viewModel.DailyServerAggregationVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerService implements IServerService {
    @Autowired
    DailyServerAggregationRepository dailyServerAggregationRepository;

    public DailyServerAggregationVM getLastServerAggregation()
    {
        DailyServerAggregation dailyServerAggregation = getLastDailyServerAggregation();
        DailyServerAggregationVM dailyServerAggregationVM;
        dailyServerAggregationVM = DailyServerAggregationVM.fromDailyServerAggregation(dailyServerAggregation);
        return dailyServerAggregationVM;
    }

    public DailyServerAggregationVM getLastServerAggregation(AggregationDimentionEnum dimention)
    {
        DailyServerAggregation dailyServerAggregation = getLastDailyServerAggregation();
        DailyServerAggregationVM dailyServerAggregationVM;
        dailyServerAggregationVM = DailyServerAggregationVM.fromDailyServerAggregation(dailyServerAggregation,dimention);
        return dailyServerAggregationVM;
    }
    private DailyServerAggregation getLastDailyServerAggregation() {
        long timestamp = DateUtil.getZero();
        DailyServerAggregationVM dailyServerAggregationVM = null;
        DailyServerAggregation dailyServerAggregation = null;
        List<DailyServerAggregation> dailyServerAggregations = dailyServerAggregationRepository.findByTimestamp(timestamp);
        if(EmptyUtil.isNotEmpty(dailyServerAggregations))
        {
            dailyServerAggregation = dailyServerAggregations.get(0);
        }else{
            List<DailyServerAggregation> lastDailyServerAggregations = dailyServerAggregationRepository.findAll(Sort.by(Sort.Order.desc("timestamp")));
            if(EmptyUtil.isNotEmpty(lastDailyServerAggregations))
            {
                dailyServerAggregation = lastDailyServerAggregations.get(0);
            }
        }
        return dailyServerAggregation;
    }
}
