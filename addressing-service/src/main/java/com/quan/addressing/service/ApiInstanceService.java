package com.quan.addressing.service;

import com.quan.addressing.model.ApiInstanceModel;
import com.quan.addressing.model.ApiInstanceResult;

import java.util.List;
import java.util.Map;

public interface ApiInstanceService {
    Map<String, List<ApiInstanceResult>> selectInstanceByAppId(List<String> appIds);

    String insertInstance(List<ApiInstanceModel> apiInstanceModels);
}