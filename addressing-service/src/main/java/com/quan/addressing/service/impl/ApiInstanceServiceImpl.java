package com.quan.addressing.service.impl;


import com.quan.addressing.dao.AppMetaDao;
import com.quan.addressing.entity.ApiInstanceSelectResult;
import com.quan.addressing.entity.ApiInstanceEntiry;
import com.quan.addressing.model.ApiInstanceModel;
import com.quan.addressing.util.InstanceHelper;
import com.quan.addressing.dao.ApiInstanceDao;
import com.quan.addressing.model.ApiInstanceResult;
import com.quan.addressing.service.ApiInstanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ApiInstanceServiceImpl implements ApiInstanceService {

    @Autowired
    AppMetaDao appMetaDao;

    @Autowired
    ApiInstanceDao apiInstanceDao;

    @Override
    public Map<String, List<ApiInstanceResult>> selectInstanceByAppName(List<String> appNames) {
        List<ApiInstanceSelectResult> apiInstanceSelectResults = apiInstanceDao.selectInstanceByAppName(appNames);
        Map<String, List<ApiInstanceResult>> resultMap = toMap(apiInstanceSelectResults);
        return resultMap;
    }

    @Override
    @Transactional
    public String insertInstance(ApiInstanceModel apiInstanceModel) {
        String result = "";
        //先查询AppName插叙appId
        Long appId = appMetaDao.selectAppMetaGetId(apiInstanceModel.getAppName());
        //
        ApiInstanceEntiry apiInstanceEntiry = new ApiInstanceEntiry();
        BeanUtils.copyProperties(apiInstanceModel, apiInstanceEntiry);
        if (!Objects.isNull(appId)) {
            apiInstanceEntiry.setAppId(appId);
            try {
                apiInstanceDao.insertInstance(apiInstanceEntiry);
                result = "ALL instance add successful";
            } catch (Exception e) {
                e.printStackTrace();
                if (e.getClass() == DuplicateKeyException.class) {
                    result = "Some appId's interface is already exist,please use update";
                }
            }
        } else {
            result = "please confirm you appId had exist in app_Meta_table";
        }
        return result;
    }

    @Override
    public String deleteInstance(String appName, String apiName) {
        Integer count = 0;
        Long appId =appMetaDao.selectAppMetaGetId(appName);
        if (Objects.nonNull(appId)){
            count = apiInstanceDao.deleteInstance(appId, apiName);
        }
        return "had delete " + count + "data";
    }

    @Override
    public String updateInstance(ApiInstanceModel apiInstanceModel) {
        Integer count = 0;
        ApiInstanceEntiry apiInstanceEntiry = new ApiInstanceEntiry();
        BeanUtils.copyProperties(apiInstanceModel, apiInstanceEntiry);
        Long appId = appMetaDao.selectAppMetaGetId(apiInstanceModel.getAppName());
        apiInstanceEntiry.setAppId(appId);
        if (Objects.nonNull(appId)){
            count = apiInstanceDao.updateInstance(apiInstanceEntiry);
        }
        return "had update " + count + "data";
    }


    private Map<String, List<ApiInstanceResult>> toMap(List<ApiInstanceSelectResult> apiInstanceSelectResults) {
        Map<String, List<ApiInstanceSelectResult>> instanceMap = new HashMap<>();

        for (ApiInstanceSelectResult apiInstanceSelectResult : apiInstanceSelectResults) {
            if (instanceMap.containsKey(apiInstanceSelectResult.getKeyName())) {
                instanceMap.get(apiInstanceSelectResult.getKeyName()).add(apiInstanceSelectResult);
                continue;
            }
            List<ApiInstanceSelectResult> apiInstances1SelectResult = new ArrayList<>();
            apiInstances1SelectResult.add(apiInstanceSelectResult);
            instanceMap.put(apiInstanceSelectResult.getKeyName(), apiInstances1SelectResult);
        }
        return toInstanceResult(instanceMap);
    }

    private Map<String, List<ApiInstanceResult>> toInstanceResult(Map<String, List<ApiInstanceSelectResult>> apiInstancesMap) {
        Map<String, List<ApiInstanceResult>> apiInstanceResults = new HashMap<>();

        for (String key : apiInstancesMap.keySet()) {
            InstanceHelper.sortByPriority(apiInstancesMap.get(key));//排完序后进行组装
            List<ApiInstanceSelectResult> instanceList = apiInstancesMap.get(key);

            List<ApiInstanceResult> results = instanceList.stream().map(apiInstanceSelectResult ->
            {
                ApiInstanceResult apiInstanceResult = new ApiInstanceResult();
                BeanUtils.copyProperties(apiInstanceSelectResult, apiInstanceResult);
                return apiInstanceResult;
            }).distinct().collect(Collectors.toList());

            apiInstanceResults.put(key, results);
        }
        return apiInstanceResults;
    }

//    private Boolean haveAppMeta(List<ApiInstanceModel> apiInstanceModels) {
//        List<String> appNames = apiInstanceModels.stream()
//                .map(apiInstanceModel -> apiInstanceModel.getAppName())
//                .collect(Collectors.toList());
//        List<AppMetaEntity> appMetaEntities = appMetaDao.selectAppMeta(appNames);
//        List<Long> appIds = appMetaEntities.stream().map(appMetaEntity -> appMetaEntity.getId()).collect(Collectors.toList());
//        return appMetaDao.selectAppMeta(appNames).size() == appNames.size() ? true : false;
//    }
}
