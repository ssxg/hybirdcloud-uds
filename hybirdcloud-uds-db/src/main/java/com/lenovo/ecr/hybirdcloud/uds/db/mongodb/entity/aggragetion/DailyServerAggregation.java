package com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("dailyServerAggregation")
public class DailyServerAggregation {
    @Id
    private String id;

    @Indexed(unique = true)
    @Field
    private long timestamp;

    @Field
    private List<DailyServiceLineServerAggregation> serviceLines;

    @Field
    private List<DailyCloudPlatformServerAggregation> cloudPlatforms;

    public String getId(){return id;}
    public void setId(String id){
        this.id = id;
    }
    public long getTimestamp(){return timestamp;}
    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }
    public List<DailyServiceLineServerAggregation> getServiceLines(){
        return serviceLines;
    }
    public void setServiceLines(List<DailyServiceLineServerAggregation> serviceLines)
    {
        this.serviceLines = serviceLines;
    }
    public List<DailyCloudPlatformServerAggregation> getCloudPlatforms(){
        return cloudPlatforms;
    }
    public void setCloudPlatforms(List<DailyCloudPlatformServerAggregation> cloudPlatforms)
    {
        this.cloudPlatforms = cloudPlatforms;
    }
}
