package com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw;

import org.springframework.boot.actuate.endpoint.annotation.FilteredEndpoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Document(collection = "server")
public class Server implements Serializable {
    @Id
    private String id;

    @Field
    private int cpu;

    @Field
    private int memory;

    @Field
    private int disk;

    @Field
    private String serviceline;

    @Field
    private String brand;

    @Field
    private long timestamp;

    @Field
    private Date createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id ;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu ;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public String getServiceline() {
        return serviceline;
    }

    public void setServiceline(String serviceline) {
        this.serviceline = serviceline ;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand ;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp ;
    }

    public Date getCreatetime(){return createtime;}

    public void setCreatetime(Date createtime){this.createtime = createtime;}
}
