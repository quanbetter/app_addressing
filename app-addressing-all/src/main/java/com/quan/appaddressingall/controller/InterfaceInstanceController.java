package com.quan.appaddressingall.controller;

import com.quan.appaddressingall.dao.InterfaceInstanceDao;
import com.quan.appaddressingall.entity.InterfaceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.quan.appaddressingall.common.Constant.COLON;


@RestController
@RequestMapping("/interface")
public class InterfaceInstanceController {
    Logger logger = LoggerFactory.getLogger(InterfaceInstanceController.class);

    @Autowired
    InterfaceInstanceDao interfaceInstanceDao;

    @RequestMapping("/getInstanceByKey")
    public List<InterfaceInstance> getInstance(@RequestParam("appKey") String appKey) {
        List<InterfaceInstance> interfaceInstances = interfaceInstanceDao.selectInstanceByAppKey(appKey);
        return interfaceInstances;
    }

    @RequestMapping("/getAllInstance")
    public Map<String, List<String>> getAllInstance() {
        List<InterfaceInstance> interfaceInstances = interfaceInstanceDao.selectAllInstance();
        logger.info("getAllInstance---count:{}",interfaceInstances.size());
        return instanceListToMap(interfaceInstances);
    }

    private Map<String, List<String>> instanceListToMap(List<InterfaceInstance> instances) {
        Map<String, List<String>> instanceMap = new HashMap<>();
        for (InterfaceInstance instance : instances) {
            if (instanceMap.containsKey(instance.getKeyName())) {
                instanceMap.get(instance.getKeyName()).add(instance.getAddress() + COLON + instance.getPort().toString());
                break;
            }
            List<String> instanceList = new ArrayList<>();
            instanceList.add(instance.getAddress() + COLON + instance.getPort());
            instanceMap.put(instance.getKeyName(), instanceList);
        }
        return instanceMap;
    }
}
