package com.quan.appaddressingall.service.impl;

import com.quan.appaddressingall.common.InstanceHelper;
import com.quan.appaddressingall.dao.InterfaceInstanceDao;
import com.quan.appaddressingall.entity.InterfaceInstance;
import com.quan.appaddressingall.service.InterfaceInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.quan.appaddressingall.common.Constant.COLON;

@Service
public class InterfaceInstanceServiceImpl implements InterfaceInstanceService {

    @Autowired
    InterfaceInstanceDao interfaceInstanceDao;

    @Override
    public Map<String, List<String>> selectAllInstance() {
        List<InterfaceInstance> interfaceInstances = interfaceInstanceDao.selectAllInstance();
        Map<String, List<String>> addrPorts = instanceListToMap(interfaceInstances);
        return addrPorts;
    }

    @Override
    public Map<String, List<String>> selectInstanceByAppId(List<String> appIds) {
        List<InterfaceInstance> interfaceInstances = interfaceInstanceDao.selectInstanceByAppId(appIds);
        return instanceListToMap(interfaceInstances);
    }

    @Override
    public Boolean addInstance(List<InterfaceInstance> instances) {
        interfaceInstanceDao.addInstance(instances);
        return true;
    }

    private Map<String, List<String>> instanceListToMap(List<InterfaceInstance> instances) {
        Map<String, List<String>> addrPortMap = new HashMap<>();
        Map<String, List<InterfaceInstance>> instanceMap = new HashMap<>();
        for (InterfaceInstance instance : instances) {
            if (instanceMap.containsKey(instance.getKeyName())) {
                instanceMap.get(instance.getKeyName()).add(instance);
                break;
            }
            List<InterfaceInstance> instanceList = new ArrayList<>();
            instanceList.add(instance);
            instanceMap.put(instance.getKeyName(), instanceList);
        }
        //sort by priority and only return address and port
        for (String s : instanceMap.keySet()) {
            InstanceHelper.sortByPriority(instanceMap.get(s));
            List<String> addrPorts = instanceMap.get(s).stream().map(in -> in.getAddress() + COLON + in.getPort()).collect(Collectors.toList());
            addrPortMap.put(s, addrPorts.stream().distinct().collect(Collectors.toList()));
        }
        return addrPortMap;
    }
}
