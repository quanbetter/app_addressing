package com.quan.appaddressingall.controller;

import com.quan.appaddressingall.entity.APIInstanceResult;
import com.quan.appaddressingall.entity.APIInstance;
import com.quan.appaddressingall.service.APIInstanceService;
import com.quan.appaddressingall.service.AppInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.quan.appaddressingall.common.Constant.DEFAULT_APP_ID;


@RestController
@RequestMapping("/interface")
public class APIInstanceController {
    Logger logger = LoggerFactory.getLogger(APIInstanceController.class);

    @Autowired
    APIInstanceService apiInstanceService;

    @Autowired
    AppInstanceService appInstanceService;

    @RequestMapping("/getInstanceByAppId")
    public Map<String, List<APIInstanceResult>> getInstance(@RequestBody List<String> appId) {
        if (appId.size() ==1 & appId.get(0) == DEFAULT_APP_ID){
            Map<String, List<APIInstanceResult>> instances = apiInstanceService.selectInstanceByAppId(appId);
            logger.info("already get API instance {}",instances.size());
            return instances;
        }else {
            return appInstanceService.getDefaultAppInstance();
        }

    }

    @RequestMapping("/getAllInstance")
    public Map<String, List<String>> getAllInstance() {
        Map<String, List<String>> addrPorts = apiInstanceService.selectAllInstance();
        logger.info("getAllInstance---count:{}", addrPorts.size());
        return addrPorts;
    }

    @RequestMapping("/addInstance")
    public String addInstance(@RequestBody List<APIInstance> instances){
        return apiInstanceService.addInstance(instances);
    }

}
