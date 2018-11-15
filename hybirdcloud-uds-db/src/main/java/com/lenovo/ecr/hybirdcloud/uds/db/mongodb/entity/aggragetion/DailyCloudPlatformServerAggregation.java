package com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.aggragetion;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class DailyCloudPlatformServerAggregation {
    @Field
    private String cloudPlatformId;

    @Field
    private String name;

    @Field
    private String fname;

    @Field
    private int count;

    @Field
    private int cpu;

    @Field
    private int memory;

    @Field
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
