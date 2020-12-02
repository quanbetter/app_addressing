package com.quan.appaddressingall.service;

import com.quan.appaddressingall.entity.APIInstance;
import com.quan.appaddressingall.entity.APIInstanceResult;

import java.util.List;
import java.util.Map;

public interface APIInstanceService {
    Map<String, List<String>> selectAllInstance();

    Map<String, List<APIInstanceResult>> selectInstanceByAppId(List<String> appIds);

    String addInstance(List<APIInstance> instances);
}
