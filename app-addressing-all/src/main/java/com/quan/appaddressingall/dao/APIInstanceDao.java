package com.quan.appaddressingall.dao;

import com.quan.appaddressingall.entity.APIInstance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface APIInstanceDao {
    List<APIInstance> selectAllInstance();

    List<APIInstance> selectInstanceByAppId(List<String> appIds);

    Integer addInstance(List<APIInstance> instances);
}
