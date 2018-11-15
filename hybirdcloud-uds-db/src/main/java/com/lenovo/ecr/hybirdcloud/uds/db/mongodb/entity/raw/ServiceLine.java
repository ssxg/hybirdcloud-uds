package com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document
public class ServiceLine {
    @Id
    private String id;

    @Field
    private String enname;

    @Field
    private String cnname;

    public String getId(){return id;}
    public void setId(String id){
        this.id = id;
    }
    public String getEnname(){return enname;}
    public void setEnname(String enname){
        this.enname = enname;
    }
    public String getCnname(){return cnname;}
    public void setCnname(String cnname){
        this.cnname = cnname;
    }
}
