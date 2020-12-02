package com.quan.appaddressingall.controller;

import com.quan.appaddressingall.entity.APIInstanceResult;
import com.quan.appaddressingall.entity.APIInstance;
import com.quan.appaddressingall.service.APIInstanceService;
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
public class APIInstanceController {
    Logger logger = LoggerFactory.getLogger(APIInstanceController.class);

    @Autowired
    APIInstanceService APIInstanceService;

    @RequestMapping("/getInstanceByAppId")
    public Map<String, List<APIInstanceResult>> getInstance(@RequestBody List<String> appId) {
        Map<String, List<APIInstanceResult>> instances = APIInstanceService.selectInstanceByAppId(appId);
        logger.info("already get API instance {}",instances.size());
        return instances;
    }

    @RequestMapping("/getAllInstance")
    public Map<String, List<String>> getAllInstance() {
        Map<String, List<String>> addrPorts = APIInstanceService.selectAllInstance();
        logger.info("getAllInstance---count:{}", addrPorts.size());
        return addrPorts;
    }

    @RequestMapping("/addInstance")
    public String addInstance(@RequestBody List<APIInstance> instances){
        return APIInstanceService.addInstance(instances);
    }

}
