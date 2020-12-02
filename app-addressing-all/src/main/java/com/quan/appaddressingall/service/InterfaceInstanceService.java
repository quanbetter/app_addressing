package com.quan.appaddressingall.service;

import com.quan.appaddressingall.entity.INFInstanceResult;
import com.quan.appaddressingall.entity.InterfaceInstance;

import java.util.List;
import java.util.Map;

public interface InterfaceInstanceService {
    Map<String, List<String>> selectAllInstance();

    Map<String, List<INFInstanceResult>> selectInstanceByAppId(List<String> appIds);

    String addInstance(List<InterfaceInstance> instances);
}
