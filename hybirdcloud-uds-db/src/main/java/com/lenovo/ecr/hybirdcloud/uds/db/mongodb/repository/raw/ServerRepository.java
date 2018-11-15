package com.lenovo.ecr.hybirdcloud.uds.db.mongodb.repository.raw;

import com.lenovo.ecr.hybirdcloud.uds.db.mongodb.entity.raw.Server;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends MongoRepository<Server,String> {
}
