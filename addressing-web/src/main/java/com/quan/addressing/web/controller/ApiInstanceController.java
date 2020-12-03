package com.quan.addressing.web.controller;


import com.quan.addressing.common.Constant;
import com.quan.addressing.entity.ApiInstance;
import com.quan.addressing.model.ApiInstanceModel;
import com.quan.addressing.model.ApiInstanceResult;
import com.quan.addressing.service.ApiInstanceService;
import com.quan.addressing.service.AppInstanceService;
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
public class ApiInstanceController {
    Logger logger = LoggerFactory.getLogger(ApiInstanceController.class);

    @Autowired
    ApiInstanceService apiInstanceService;

    @Autowired
    AppInstanceService appInstanceService;

    @RequestMapping("/getInstanceByAppId")
    public Map<String, List<ApiInstanceResult>> getInstance(@RequestBody List<String> appId) {
            Map<String, List<ApiInstanceResult>> instances = apiInstanceService.selectInstanceByAppId(appId);
            logger.info("already get API instance {}",instances.size());
            return instances;
    }

    @RequestMapping("/addInstance")
    public String addInstance(@RequestBody List<ApiInstanceModel> apiInstanceModels){
        return apiInstanceService.insertInstance(apiInstanceModels);
    }

}
