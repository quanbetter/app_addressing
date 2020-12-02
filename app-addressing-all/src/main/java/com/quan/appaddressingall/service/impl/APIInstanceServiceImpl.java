package com.quan.appaddressingall.service.impl;

import com.quan.appaddressingall.common.InstanceHelper;
import com.quan.appaddressingall.dao.AppInstanceDao;
import com.quan.appaddressingall.dao.APIInstanceDao;
import com.quan.appaddressingall.entity.APIInstance;
import com.quan.appaddressingall.entity.APIInstanceResult;
import com.quan.appaddressingall.service.APIInstanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class APIInstanceServiceImpl implements APIInstanceService {

    @Autowired
    AppInstanceDao appInstanceDao;

    @Autowired
    APIInstanceDao APIInstanceDao;

    @Override
    public Map<String, List<String>> selectAllInstance() {
        List<APIInstance> APIInstances = APIInstanceDao.selectAllInstance();
//        Map<String, List<String>> addrPorts = instanceListToMap(APIInstances);
        return null;
    }

    @Override
    public Map<String, List<APIInstanceResult>> selectInstanceByAppId(List<String> appIds) {
        List<APIInstance> APIInstances = APIInstanceDao.selectInstanceByAppId(appIds);
        List<APIInstanceResult> appAPIInstanceResults = appInstanceDao.getDefaultAppInstance();
        Map<String, List<APIInstanceResult>>  resultMap = toMap(APIInstances);
        resultMap.put("default", appAPIInstanceResults);
        return resultMap;
    }

    @Override
    public String addInstance(List<APIInstance> instances) {
        Integer count = APIInstanceDao.addInstance(instances);
        if (count != instances.size()){
            return "some instance didn't add successful";
        }
        return "add all successful";
    }

    private Map<String, List<APIInstanceResult>> toMap(List<APIInstance> apiInstances) {
        Map<String, List<APIInstance>> instanceMap = new HashMap<>();
        for (APIInstance apiInstance : apiInstances) {
            if (instanceMap.containsKey(apiInstance.getKeyNameVersion())) {
                instanceMap.get(apiInstance.getKeyNameVersion()).add(apiInstance);
                continue;
            }
            List<APIInstance> APIInstances = new ArrayList<>();
            APIInstances.add(apiInstance);
            instanceMap.put(apiInstance.getKeyNameVersion(), APIInstances);
        }

        return  toInstanceResult(instanceMap);
    }

    private Map<String, List<APIInstanceResult>> toInstanceResult(Map<String, List<APIInstance>> apiInstancesMap ){
        Map<String, List<APIInstanceResult>> apiInstanceResults  = new HashMap<>();

        for (String key : apiInstancesMap.keySet()) {
            InstanceHelper.sortByPriority(apiInstancesMap.get(key));//排完序后进行组装
            List<APIInstance> instanceList = apiInstancesMap.get(key);

            List<APIInstanceResult> results =  instanceList.stream().map(s->
            {
                APIInstanceResult apiInstanceResult = new APIInstanceResult();
                BeanUtils.copyProperties(s, apiInstanceResult);
                return apiInstanceResult;
            }).collect(Collectors.toList());

            apiInstanceResults.put(key,results);
        }
        return apiInstanceResults;
    }
}
