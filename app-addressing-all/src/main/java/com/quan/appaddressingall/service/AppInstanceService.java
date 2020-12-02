package com.quan.appaddressingall.service;

import com.quan.appaddressingall.entity.APIInstanceResult;

import java.util.List;
import java.util.Map;

public interface AppInstanceService {
    Map<String, List<APIInstanceResult>> getDefaultAppInstance();
}
