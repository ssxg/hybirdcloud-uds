package com.lenovo.ecr.hybirdcloud.uds.facede.model.dto;

import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw.Server;

import java.util.List;

public class RemoteServersDto {
    public int count;
    public String next;
    public String previous;
    public List<Server> results;
}
