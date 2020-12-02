package com.quan.appaddressingall.service.impl;

import com.quan.appaddressingall.common.InstanceHelper;
import com.quan.appaddressingall.dao.AppInstanceDao;
import com.quan.appaddressingall.dao.InterfaceInstanceDao;
import com.quan.appaddressingall.entity.INFInstanceResult;
import com.quan.appaddressingall.entity.InterfaceInstance;
import com.quan.appaddressingall.service.InterfaceInstanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InterfaceInstanceServiceImpl implements InterfaceInstanceService {

    @Autowired
    AppInstanceDao appInstanceDao;

    @Autowired
    InterfaceInstanceDao interfaceInstanceDao;

    @Override
    public Map<String, List<String>> selectAllInstance() {
        List<InterfaceInstance> interfaceInstances = interfaceInstanceDao.selectAllInstance();
//        Map<String, List<String>> addrPorts = instanceListToMap(interfaceInstances);
        return null;
    }

    @Override
    public Map<String, List<INFInstanceResult>> selectInstanceByAppId(List<String> appIds) {
        List<InterfaceInstance> interfaceInstances = interfaceInstanceDao.selectInstanceByAppId(appIds);
        List<INFInstanceResult> appInstanceResults = appInstanceDao.getDefaultAppInstance();
        Map<String, List<INFInstanceResult>>  resultMap = instanceListToMap(interfaceInstances);
        resultMap.put("default",appInstanceResults);
        return resultMap;
    }

    @Override
    public String addInstance(List<InterfaceInstance> instances) {
        Integer count = interfaceInstanceDao.addInstance(instances);
        if (count != instances.size()){
            return "some instance didn't add successful";
        }
        return "add all successful";
    }

    private Map<String, List<INFInstanceResult>> instanceListToMap(List<InterfaceInstance> instances) {
        Map<String, List<INFInstanceResult>> results = new HashMap<>();
        Map<String, List<InterfaceInstance>> instanceMap = new HashMap<>();
        for (InterfaceInstance instance : instances) {
            if (instanceMap.containsKey(instance.getKeyNameVersion())) {
                instanceMap.get(instance.getKeyNameVersion()).add(instance);
                continue;
            }
            List<InterfaceInstance> interfaceInstances = new ArrayList<>();
            interfaceInstances.add(instance);
            instanceMap.put(instance.getKeyNameVersion(), interfaceInstances);
        }

        //sort by priority and only return address and port
        for (String key : instanceMap.keySet()) {
            InstanceHelper.sortByPriority(instanceMap.get(key));//排完序后进行组装
            List<InterfaceInstance> instanceList = instanceMap.get(key);
            List<INFInstanceResult> re =  instanceList.stream().map(s->
            {INFInstanceResult infInstanceResult = new INFInstanceResult();
            BeanUtils.copyProperties(s,infInstanceResult);
            return infInstanceResult;
            }).collect(Collectors.toList());
            results.put(key,re);
        }
        return results;
    }
}
