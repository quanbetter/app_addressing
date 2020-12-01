package com.quan.appaddressingall.service;

import com.quan.appaddressingall.entity.InterfaceInstance;

import java.util.List;
import java.util.Map;

public interface InterfaceInstanceService {
    Map<String, List<String>> selectAllInstance();

    Map<String, List<String>> selectInstanceByAppId(List<String> appIds);

    Boolean addInstance(List<InterfaceInstance> instances);
}
