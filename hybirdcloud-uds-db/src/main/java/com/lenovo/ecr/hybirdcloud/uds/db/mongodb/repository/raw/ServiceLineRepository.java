package com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.raw;

import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw.Server;
import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw.ServiceLine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ServiceLineRepository extends MongoRepository<ServiceLine,String> {
    List<ServiceLine> findByEnname(String enname);
}
