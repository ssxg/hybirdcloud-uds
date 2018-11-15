package com.lenovo.ecr.hybirdcloud.uds.facede.model.viewModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion.DailyServerAggregation;
import com.lenovo.ecr.hybirdcloud.uds.facede.enums.AggregationDimentionEnum;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DailyServerAggregationVM {
    public static DailyServerAggregationVM  fromDailyServerAggregation(DailyServerAggregation aggragation)
    {
        AggregationDimentionEnum dimention = AggregationDimentionEnum.ALL;
        return fromDailyServerAggregation(aggragation,dimention);
    }
    public static DailyServerAggregationVM  fromDailyServerAggregation(DailyServerAggregation aggragation,AggregationDimentionEnum dimention)
    {
        List<String> ignoreProperties = new LinkedList<>();
        switch (dimention){
            case ALL:
                break;
            case SERVICE_LINE:
                ignoreProperties.add("cloudPlatforms");
                break;
            case CLOUD_PLATFORM:
                ignoreProperties.add("serviceLines");
                break;
        }
        String[] ignories = ignoreProperties.toArray(new String[ignoreProperties.size()]);
        DailyServerAggregationVM dailyServerAggregationVM = new DailyServerAggregationVM();
        BeanUtils.copyProperties(aggragation,dailyServerAggregationVM,ignories);
        return dailyServerAggregationVM;
    }

    private String id;

    private long timestamp;

    private List<DailyServiceLineServerAggregationVM> serviceLines;

    private List<DailyCloudPlatformServerAggregationVM> cloudPlatforms;

    public String getId(){return id;}
    public void setId(String id){
        this.id = id;
    }
    public long getTimestamp(){return timestamp;}
    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }
    public List<DailyServiceLineServerAggregationVM> getServiceLines(){
        return serviceLines;
    }
    public void setServiceLines(List<DailyServiceLineServerAggregationVM> serviceLines)
    {
        this.serviceLines = serviceLines;
    }
    public List<DailyCloudPlatformServerAggregationVM> getCloudPlatforms(){
        return cloudPlatforms;
    }
    public void setCloudPlatforms(List<DailyCloudPlatformServerAggregationVM> cloudPlatforms)
    {
        this.cloudPlatforms = cloudPlatforms;
    }

}

class DailyCloudPlatformServerAggregationVM {
    private String cloudPlatformId;

    private String name;

    private String fname;

    private int count;

    private int cpu;

    private int memory;

    private int disk;

    public String getCloudPlatformId(){return cloudPlatformId;}
    public void setCloudPlatformId(String cloudPlatformId){
        this.cloudPlatformId = cloudPlatformId;
    }
    public String getName(){return name;}
    public void setName(String name){
        this.name = name;
    }
    public String getFname(){return fname;}
    public void setFname(String fname){
        this.fname = fname;
    }
    public int getCount(){return count;}
    public void setCount(int count){
        this.count = count;
    }
    public int getCpu(){return cpu;}
    public void setCpu(int cpu){
        this.cpu = cpu;
    }
    public int getMemory(){return memory;}
    public void setMemory(int memory){
        this.memory = memory;
    }
    public int getDisk(){return disk;}
    public void setDisk(int disk){
        this.disk = disk;
    }
}

class DailyServiceLineServerAggregationVM {
    private String serviceLineId;

    private String name;

    private String fname;

    private int count;

    private int cpu;

    private int memory;

    private int disk;

    private List<DailyCPinSLAgrregationVM> cloudPlatforms;

    public String getServiceLineId(){return serviceLineId;}
    public void setServiceLineId(String serviceLineId){
            this.serviceLineId = serviceLineId;
            }
    public String getName(){return name;}
    public void setName(String name){
            this.name = name;
            }
    public String getFname(){return fname;}
    public void setFname(String fname){
            this.fname = fname;
            }
    public int getCount(){return count;}
    public void setCount(int count){
            this.count = count;
            }
    public int getCpu(){return cpu;}
    public void setCpu(int cpu){
            this.cpu = cpu;
            }
    public int getMemory(){return memory;}
    public void setMemory(int memory){
            this.memory = memory;
            }
    public int getDisk(){return disk;}
    public void setDisk(int disk){
            this.disk = disk;
            }
    public List<DailyCPinSLAgrregationVM> getCloudPlatforms(){
            return cloudPlatforms;
            }
    public void setCloudPlatforms(List<DailyCPinSLAgrregationVM> cloudPlatforms)
            {
            this.cloudPlatforms = cloudPlatforms;
            }
}

class DailyCPinSLAgrregationVM {
    private String cloudPlatformId;

    private int count;

    private int cpu;

    private int memory;

    private int disk;

    public String getCloudPlatformId(){return cloudPlatformId;}
    public void setCloudPlatformId(String cloudPlatformId){
        this.cloudPlatformId = cloudPlatformId;
    }
    public int getCount(){return count;}
    public void setCount(int count){
        this.count = count;
    }
    public int getCpu(){return cpu;}
    public void setCpu(int cpu){
        this.cpu = cpu;
    }
    public int getMemory(){return memory;}
    public void setMemory(int memory){
        this.memory = memory;
    }
    public int getDisk(){return disk;}
    public void setDisk(int disk){
        this.disk = disk;
    }
}
