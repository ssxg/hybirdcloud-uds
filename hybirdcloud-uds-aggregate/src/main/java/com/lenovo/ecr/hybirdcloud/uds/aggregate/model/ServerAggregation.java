package com.lenovo.ecr.hybirdcloud.uds.aggregate.model;

public class ServerAggregation {
    private String serviceline;
    private String platform;
    private int count;
    private int cpu;
    private int memory;
    private int disk;
    private long timestamp;

    public String getServiceline(){return serviceline;}
    public void setServiceline(String serviceline){
        this.serviceline = serviceline;
    }
    public String getPlatform(){return platform;}
    public void setPlatform(String platform){
        this.platform = platform;
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
    public long getTimestamp(){return timestamp;}
    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }
}
