package com.quan.addressing.service.impl;


import com.quan.addressing.dao.AppMetaDao;
import com.quan.addressing.entity.ApiInstance;
import com.quan.addressing.entity.ApiInstanceEntiry;
import com.quan.addressing.model.ApiInstanceModel;
import com.quan.addressing.util.InstanceHelper;
import com.quan.addressing.dao.ApiInstanceDao;
import com.quan.addressing.dao.AppInstanceDao;
import com.quan.addressing.model.ApiInstanceResult;
import com.quan.addressing.service.ApiInstanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiInstanceServiceImpl implements ApiInstanceService {

    @Autowired
    AppMetaDao appMetaDao;

    @Autowired
    ApiInstanceDao apiInstanceDao;

    @Override
    public Map<String, List<ApiInstanceResult>> selectInstanceByAppId(List<String> appIds) {
        List<ApiInstance> ApiInstances = apiInstanceDao.selectInstanceByAppId(appIds);
        Map<String, List<ApiInstanceResult>> resultMap = toMap(ApiInstances);
        return resultMap;
    }

    @Override
    @Transactional
    public String insertInstance(List<ApiInstanceModel> apiInstanceModels) {
        List<ApiInstanceEntiry> instanceEntiries = apiInstanceModels.stream().map(apiInstanceModel -> {
            ApiInstanceEntiry apiInstanceEntiry = new ApiInstanceEntiry();
            BeanUtils.copyProperties(apiInstanceModel, apiInstanceEntiry);
            return apiInstanceEntiry;
        }).collect(Collectors.toList());

        if (haveAppMeta(apiInstanceModels)){

            Integer count = apiInstanceDao.insertInstance(instanceEntiries);
            if (count != instanceEntiries.size()) {
                return "ALL instance didn't add successful";
            }
            return "fail";
        }
        return "add all successful";
    }


    private Map<String, List<ApiInstanceResult>> toMap(List<ApiInstance> apiInstances) {
        Map<String, List<ApiInstance>> instanceMap = new HashMap<>();

        for (ApiInstance apiInstance : apiInstances) {
            if (instanceMap.containsKey(apiInstance.getKeyName())) {
                instanceMap.get(apiInstance.getKeyName()).add(apiInstance);
                continue;
            }
            List<ApiInstance> apiInstances1 = new ArrayList<>();
            apiInstances1.add(apiInstance);
            instanceMap.put(apiInstance.getKeyName(), apiInstances1);
        }

        return toInstanceResult(instanceMap);
    }

    private Map<String, List<ApiInstanceResult>> toInstanceResult(Map<String, List<ApiInstance>> apiInstancesMap) {
        Map<String, List<ApiInstanceResult>> apiInstanceResults = new HashMap<>();

        for (String key : apiInstancesMap.keySet()) {
            InstanceHelper.sortByPriority(apiInstancesMap.get(key));//排完序后进行组装
            List<ApiInstance> instanceList = apiInstancesMap.get(key);

            List<ApiInstanceResult> results = instanceList.stream().map(apiInstance ->
            {
                ApiInstanceResult apiInstanceResult = new ApiInstanceResult();
                BeanUtils.copyProperties(apiInstance, apiInstanceResult);
                return apiInstanceResult;
            }).distinct().collect(Collectors.toList());

            apiInstanceResults.put(key, results);
        }
        return apiInstanceResults;
    }

    private Boolean haveAppMeta(List<ApiInstanceModel> apiInstanceModels) {
        List<String> appNames = apiInstanceModels.stream()
                .map(apiInstanceModel -> apiInstanceModel.getAppName())
                .collect(Collectors.toList());
        return appMetaDao.selectAppMeta(appNames).size() == appNames.size() ? true : false;
    }
}
