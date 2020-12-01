package com.quan.appaddressingall.service;

import com.quan.appaddressingall.entity.InterfaceInstance;

import java.util.List;
import java.util.Map;

public interface InterfaceInstanceService {
    Map<String, List<String>> selectAllInstance();

    List<InterfaceInstance> selectInstanceByAppKey(String appKey);

    Boolean addInstance(List<InterfaceInstance> instances);
}
