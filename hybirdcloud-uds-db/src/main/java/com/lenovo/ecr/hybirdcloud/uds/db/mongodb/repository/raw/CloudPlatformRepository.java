package com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.raw;

import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw.CloudPlatform;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CloudPlatformRepository extends MongoRepository<CloudPlatform,String> {
    List<CloudPlatform> findByEnname(String enname);
}
