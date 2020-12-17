package com.quan.addressing.web.controller;

import com.quan.addressing.dto.ApiInstanceRequest;
import com.quan.addressing.model.ApiInstanceModel;
import com.quan.addressing.model.ApiInstanceResult;
import com.quan.addressing.service.ApiInstanceService;
import com.quan.addressing.service.AppInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/interface")
public class ApiInstanceController extends BaseController {
    Logger logger = LoggerFactory.getLogger(ApiInstanceController.class);

    @Autowired
    ApiInstanceService apiInstanceService;

    @Autowired
    AppInstanceService appInstanceService;

    @RequestMapping("/getInstanceByAppName")
    public Map<String, List<ApiInstanceResult>> getInstance(@Valid @RequestBody List<String> appNames) {
        Map<String, List<ApiInstanceResult>> instances = apiInstanceService.selectInstanceByAppName(appNames);
        logger.info("already get API instance {}", instances.size());
        return instances;
    }

    @RequestMapping("/addInstance")
    public Object addInstance(@Valid @RequestBody ApiInstanceRequest apiInstanceRequest, BindingResult bindingResult) {
        if (!checkParameter(bindingResult)) {
            return jsonObject;
        }
        ApiInstanceModel apiInstanceModel = new ApiInstanceModel();
        BeanUtils.copyProperties(apiInstanceRequest, apiInstanceModel);
        return apiInstanceService.insertInstance(apiInstanceModel);
    }

    @RequestMapping("/delete")
    public String update(@RequestParam("appName") String appName, @RequestParam("apiName") String apiName) {
        return apiInstanceService.deleteInstance(appName, apiName);
    }

    @RequestMapping("/update")
    public Object update(@Valid @RequestBody ApiInstanceRequest apiInstanceRequest, BindingResult bindingResult) {
        if (!checkParameter(bindingResult)) {
            return jsonObject;
        }
        ApiInstanceModel apiInstanceModel = new ApiInstanceModel();
        BeanUtils.copyProperties(apiInstanceRequest, apiInstanceModel);
        return apiInstanceService.updateInstance(apiInstanceModel);
    }
}
