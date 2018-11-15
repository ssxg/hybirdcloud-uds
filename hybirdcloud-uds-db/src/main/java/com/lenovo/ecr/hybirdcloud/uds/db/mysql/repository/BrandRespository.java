package com.lenovo.ecr.hybirdcloud.uds.db.mysql.repository;

import com.lenovo.ecr.hybirdcloud.uds.db.mysql.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRespository extends JpaRepository<Brand,String> {

}
