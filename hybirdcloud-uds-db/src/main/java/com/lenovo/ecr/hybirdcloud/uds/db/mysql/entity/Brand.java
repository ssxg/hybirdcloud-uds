package com.lenovo.ecr.hybirdcloud.uds.db.mysql.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="brand")
public class Brand {
    @Id
    @Column(name="id",length = 32)
    @GenericGenerator(name="uuidGenerator", strategy="uuid")
    @GeneratedValue(generator = "uuidGenerator")
    private String id;

    @Column(name="name",length = 50,nullable = false,unique = true)
    private String name;

    @Column(name="type",length = 50,nullable = false)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}
