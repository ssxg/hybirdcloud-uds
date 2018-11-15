package com.lenovo.ecr.hybirdcloud.uds.aggregate.service;

import com.lenovo.ecr.hybirdcloud.uds.aggregate.model.ServerAggregation;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyCPinSLAgrregation;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyCloudPlatformServerAggregation;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyServerAggregation;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyServiceLineServerAggregation;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw.CloudPlatform;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw.ServiceLine;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.aggregation.DailyServerAggregationRepository;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.raw.CloudPlatformRepository;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.raw.ServiceLineRepository;
import com.lenovo.ecr.hybirdcloud.uds.common.util.EmptyUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import sun.invoke.empty.Empty;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class MongoServerAggregationService implements IServerAggregationService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ServiceLineRepository serviceLineRepository;

    @Autowired
    CloudPlatformRepository cloudPlatformRepository;

    @Autowired
    DailyServerAggregationRepository dailyServerAggregationRepository;

    public List<ServerAggregation> aggregate(long timestamp){
        List<ServerAggregation> server_aggregations = new LinkedList<ServerAggregation>();
        Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("timestamp").is(timestamp)),Aggregation.group("serviceline","brand").count().as("count").sum("cpu").as("cpu").sum("memory").as("memory").sum("disk").as("disk"));
        AggregationResults<Document> results = mongoTemplate.aggregate(agg, "server", Document.class);
        List<Document> documents = results.getMappedResults();
        if(!EmptyUtil.isEmpty(documents))
        {
            for(int i=0;i<documents.size();i++)
            {
                ServerAggregation server_aggregation = new ServerAggregation();
                Document service_line_result = documents.get(i);
                server_aggregation.setServiceline((String) service_line_result.get("serviceline"));
                server_aggregation.setPlatform((String) service_line_result.get("brand"));
                server_aggregation.setCpu((int)service_line_result.get("cpu"));
                server_aggregation.setMemory((int)service_line_result.get("memory"));
                server_aggregation.setDisk((int)service_line_result.get("disk"));
                server_aggregation.setCount((int)service_line_result.get("count"));
                server_aggregation.setTimestamp(timestamp);
                server_aggregations.add(server_aggregation);
            }
        }
        return server_aggregations;
    }

    public DailyServerAggregation parseAggregations(List<ServerAggregation> aggregations)
    {
        DailyServerAggregation dailyServerAggregation = new DailyServerAggregation();
        long timestamp = 0;
        if(!EmptyUtil.isEmpty(aggregations))
        {
            timestamp = aggregations.get(0).getTimestamp();
        }
        dailyServerAggregation.setTimestamp(timestamp);
        Map<String,List<ServerAggregation>> ServerAggregationMap = new HashMap<>();
        for(ServerAggregation aggregation : aggregations)
        {
            if(!ServerAggregationMap.containsKey(aggregation.getServiceline()))
            {
                List<ServerAggregation> serviceLineAggregations = new LinkedList<>();
                serviceLineAggregations.add(aggregation);
                ServerAggregationMap.put(aggregation.getServiceline(),serviceLineAggregations);
            }
            else{
                List<ServerAggregation> serviceLineAggregations = ServerAggregationMap.get(aggregation.getServiceline());
                serviceLineAggregations.add(aggregation);
            }
        }
        List<DailyServiceLineServerAggregation> dailyServiceLineServerAggregations = new LinkedList<>();
        Map<String,CloudPlatform> cloudPlatformMap = getCloudPlatformInfos();
        Map<String,ServiceLine> serviceLineMap = getServiceLines();
        Map<String, DailyCloudPlatformServerAggregation> dailyCloudPlatformServerAggregationMap = new HashMap<>();
        for(String servieLineName:ServerAggregationMap.keySet() )
        {
            List<ServerAggregation> serviceLineAggregations = ServerAggregationMap.get(servieLineName);
            int cpu = 0;
            int memory = 0;
            int disk = 0;
            int count = 0;
            List<DailyCPinSLAgrregation> dailyCPinSLAgrregations = new LinkedList<>();
            for(ServerAggregation aggregation : serviceLineAggregations)
            {
                DailyCPinSLAgrregation dailyCPinSLAgrregation = new DailyCPinSLAgrregation();
                String cloudPlatformName = aggregation.getPlatform();
                CloudPlatform cloudPlatform = cloudPlatformMap.get(cloudPlatformName);
                if(cloudPlatform!=null)
                {
                    dailyCPinSLAgrregation.setCloudPlatformId(cloudPlatform.getId());
                }
                dailyCPinSLAgrregation.setCpu(aggregation.getCpu());
                dailyCPinSLAgrregation.setMemory(aggregation.getMemory());
                dailyCPinSLAgrregation.setDisk(aggregation.getDisk());
                dailyCPinSLAgrregation.setCount(aggregation.getCount());
                dailyCPinSLAgrregations.add(dailyCPinSLAgrregation);
                DailyCloudPlatformServerAggregation dailyCloudPlatformServerAggregation = dailyCloudPlatformServerAggregationMap.get(cloudPlatformName);
                if(dailyCloudPlatformServerAggregation== null)
                {
                    dailyCloudPlatformServerAggregation = new DailyCloudPlatformServerAggregation();
                    dailyCloudPlatformServerAggregation.setCpu(aggregation.getCpu());
                    dailyCloudPlatformServerAggregation.setMemory(aggregation.getMemory());
                    dailyCloudPlatformServerAggregation.setDisk(aggregation.getDisk());
                    dailyCloudPlatformServerAggregation.setCount(aggregation.getCount());
                    dailyCloudPlatformServerAggregation.setName(cloudPlatformName);
                    if(cloudPlatform!=null) {
                        dailyCloudPlatformServerAggregation.setFname(cloudPlatform.getCnname());
                        dailyCloudPlatformServerAggregation.setCloudPlatformId(cloudPlatform.getId());
                    }
                    dailyCloudPlatformServerAggregationMap.put(cloudPlatformName,dailyCloudPlatformServerAggregation);
                }
                else{
                    dailyCloudPlatformServerAggregation.setCpu(dailyCloudPlatformServerAggregation.getCpu()+aggregation.getCpu());
                    dailyCloudPlatformServerAggregation.setMemory(dailyCloudPlatformServerAggregation.getMemory()+aggregation.getMemory());
                    dailyCloudPlatformServerAggregation.setDisk(dailyCloudPlatformServerAggregation.getDisk()+aggregation.getDisk());
                    dailyCloudPlatformServerAggregation.setCount(dailyCloudPlatformServerAggregation.getCount()+aggregation.getCount());
                }
                cpu += aggregation.getCpu();
                memory += aggregation.getMemory();
                disk += aggregation.getDisk();
                count += aggregation.getCount();
            }
            DailyServiceLineServerAggregation dailyServiceLineServerAggregation = new DailyServiceLineServerAggregation();
            ServiceLine serviceLine = serviceLineMap.get(servieLineName);
            if(serviceLine!=null)
            {
                dailyServiceLineServerAggregation.setFname(serviceLine.getCnname());
                dailyServiceLineServerAggregation.setServiceLineId(serviceLine.getId());
            }
            dailyServiceLineServerAggregation.setName(servieLineName);
            dailyServiceLineServerAggregation.setCpu(cpu);
            dailyServiceLineServerAggregation.setMemory(memory);
            dailyServiceLineServerAggregation.setDisk(disk);
            dailyServiceLineServerAggregation.setCount(count);
            dailyServiceLineServerAggregation.setCloudPlatforms(dailyCPinSLAgrregations);
            dailyServiceLineServerAggregations.add(dailyServiceLineServerAggregation);
        }
        dailyServerAggregation.setServiceLines(dailyServiceLineServerAggregations);
        System.out.print(dailyCloudPlatformServerAggregationMap);
        List<DailyCloudPlatformServerAggregation> dailyCloudPlatformServerAggregations = new LinkedList<>();
        dailyCloudPlatformServerAggregations.addAll(dailyCloudPlatformServerAggregationMap.values());
        dailyServerAggregation.setCloudPlatforms(dailyCloudPlatformServerAggregations);
        return dailyServerAggregation;
    }

    public boolean saveDailyServerAggregation(DailyServerAggregation dailyServerAggregation)
    {
        dailyServerAggregationRepository.save(dailyServerAggregation);
        return true;
    }

    public boolean createDailyServerAggregation(long timestamp)
    {
        List<DailyServerAggregation> dailyServerAggregations = dailyServerAggregationRepository.findByTimestamp(timestamp);
        if(EmptyUtil.isNotEmpty(dailyServerAggregations))
        {
            return true;
        }
        else{
            List<ServerAggregation> serverAggregations = aggregate(timestamp);
            if(EmptyUtil.isEmpty(serverAggregations))
            {
                return false;
            }
            else{
                DailyServerAggregation dailyServerAggregation = parseAggregations(serverAggregations);
                return saveDailyServerAggregation(dailyServerAggregation);
            }
        }
    }

    private Map<String,CloudPlatform> getCloudPlatformInfos()
    {
        Map<String,CloudPlatform> cloudPlatformMap = new HashMap<>();
        List<CloudPlatform> cloudPlatforms = cloudPlatformRepository.findAll();
        for(CloudPlatform cloudPlatform:cloudPlatforms)
        {
            cloudPlatformMap.put(cloudPlatform.getEnname(),cloudPlatform);
        }
        return cloudPlatformMap;
    }

    private Map<String,ServiceLine> getServiceLines()
    {
        Map<String,ServiceLine> serviceLineHashMap = new HashMap<>();
        List<ServiceLine> serviceLines = serviceLineRepository.findAll();
        for(ServiceLine serviceLine:serviceLines)
        {
            serviceLineHashMap.put(serviceLine.getEnname(),serviceLine);
        }
        return serviceLineHashMap;
    }
}
