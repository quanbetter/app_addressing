package com.quan.appaddressingall.controller;

import com.quan.appaddressingall.entity.InterfaceInstance;
import com.quan.appaddressingall.service.InterfaceInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/interface")
public class InterfaceInstanceController {
    Logger logger = LoggerFactory.getLogger(InterfaceInstanceController.class);

    @Autowired
    InterfaceInstanceService interfaceInstanceService;

    @RequestMapping("/getInstanceByAppId")
    public Map<String, List<String>> getInstance(@RequestBody List<String> appId) {
        Map<String, List<String>> addrPorts = interfaceInstanceService.selectInstanceByAppId(appId);
        return addrPorts;
    }

    @RequestMapping("/getAllInstance")
    public Map<String, List<String>> getAllInstance() {
        Map<String, List<String>> addrPorts = interfaceInstanceService.selectAllInstance();;
        logger.info("getAllInstance---count:{}", addrPorts.size());
        return addrPorts;
    }

    @RequestMapping("/addInstance")
    public String addInstance(@RequestBody List<InterfaceInstance> instances){
        return interfaceInstanceService.addInstance(instances);
    }

}
