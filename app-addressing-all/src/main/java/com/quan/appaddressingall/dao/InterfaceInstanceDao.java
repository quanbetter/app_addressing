package com.quan.appaddressingall.dao;

import com.quan.appaddressingall.entity.InterfaceInstance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterfaceInstanceDao {
    List<InterfaceInstance> selectAllInstance();

    List<InterfaceInstance> selectInstanceByAppId(List<String> appIds);

    Integer addInstance(List<InterfaceInstance> instances);
}
