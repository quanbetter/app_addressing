package com.quan.addressing.web.controller;

import cn.lalaframework.easyopen.annotation.Api;
import cn.lalaframework.easyopen.annotation.ApiService;
import cn.lalaframework.easyopen.doc.annotation.ApiDoc;
import cn.lalaframework.easyopen.doc.annotation.ApiDocMethod;
import com.quan.addressing.dto.ApiInstanceDeleteRequest;
import com.quan.addressing.dto.ApiInstanceRequest;
import com.quan.addressing.dto.AppNamesResquest;
import com.quan.addressing.model.ApiInstanceModel;
import com.quan.addressing.model.ApiInstanceResult;
import com.quan.addressing.service.ApiInstanceService;
import com.quan.addressing.service.AppInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


@ApiService
@ApiDoc("接口实例模块")
public class ApiInstanceController {
    Logger logger = LoggerFactory.getLogger(ApiInstanceController.class);

    @Autowired
    ApiInstanceService apiInstanceService;

    @Autowired
    AppInstanceService appInstanceService;

    @Api(name = "interface", version = "getInstanceByAppName")
    @ApiDocMethod(description = "获取接口实例列表")
    public Map<String, List<ApiInstanceResult>> getApiInstance(AppNamesResquest request) {
        Map<String, List<ApiInstanceResult>> instances = apiInstanceService.selectInstanceByAppName(request.getAppNames());
        logger.info("already get API instance {}", instances.size());
        return instances;
    }

    @Api(name = "interface", version = "addInstance")
    @ApiDocMethod(description = "添加接口实例")
    public Object addApiInstance(ApiInstanceRequest apiInstanceRequest) {
        ApiInstanceModel apiInstanceModel = new ApiInstanceModel();
        BeanUtils.copyProperties(apiInstanceRequest, apiInstanceModel);
        return apiInstanceService.insertInstance(apiInstanceModel);
    }

    @Api(name = "interface", version = "delete")
    @ApiDocMethod(description = "删除接口实例")
    public String deleteApiInstance(ApiInstanceDeleteRequest request) {
        return apiInstanceService.deleteInstance(request.getAppName(), request.getApiName());
    }

    @Api(name = "interface", version = "update")
    @ApiDocMethod(description = "更新接口实例")
    public Object updateApiInstance(ApiInstanceRequest apiInstanceRequest) {
        ApiInstanceModel apiInstanceModel = new ApiInstanceModel();
        BeanUtils.copyProperties(apiInstanceRequest, apiInstanceModel);
        return apiInstanceService.updateInstance(apiInstanceModel);
    }
}
