package com.quan.addressing.web.controller;

import cn.lalaframework.easyopen.annotation.Api;
import cn.lalaframework.easyopen.annotation.ApiService;
import cn.lalaframework.easyopen.doc.annotation.ApiDoc;
import cn.lalaframework.easyopen.doc.annotation.ApiDocField;
import cn.lalaframework.easyopen.doc.annotation.ApiDocMethod;
import com.quan.addressing.dto.AppInstanceRequest;
import com.quan.addressing.model.AppInstanceModel;
import com.quan.addressing.service.AppInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ApiService
@ApiDoc("app实例模块")
public class AppInstanceController {
    Logger logger = LoggerFactory.getLogger(AppInstanceController.class);

    @Autowired
    AppInstanceService appInstanceService;

    @Api(name = "appInstance", version = "add")
    @ApiDocMethod(description = "添加app实例")
    public Object addAppInstance(AppInstanceRequest appInstanceRequest) {
        AppInstanceModel appInstanceModel = new AppInstanceModel();
        BeanUtils.copyProperties(appInstanceRequest, appInstanceModel);
        return appInstanceService.insertAppInstance(appInstanceModel);
    }

    @Api(name = "appInstance", version = "find")
    @ApiDocMethod(description = "按appName查询app实例")
    public List findByAppName(@ApiDocField(required = true, example = "quan-app-three") String appName) {
        return appInstanceService.selectAppInstance(appName);
    }

    @Api(name = "appInstance", version = "delete")
    @ApiDocMethod(description = "按appName删除app实例")
    public String deleteByAppName(@ApiDocField(required = true, example = "quan-app-three") String appName) {
        Integer count = appInstanceService.deleteAppInstance(appName);
        return "had delete " + count + " data";
    }
}
