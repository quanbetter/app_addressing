package com.quan.addressing.web.controller;

import cn.lalaframework.easyopen.annotation.Api;
import cn.lalaframework.easyopen.annotation.ApiService;
import cn.lalaframework.easyopen.doc.annotation.ApiDoc;
import cn.lalaframework.easyopen.doc.annotation.ApiDocMethod;
import com.quan.addressing.dto.AppInstanceRequest;
import com.quan.addressing.model.AppInstanceModel;
import com.quan.addressing.service.AppInstanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@ApiService
@ApiDoc("app实例模块")
public class AppInstanceController {
    @Autowired
    AppInstanceService appInstanceService;

    @Api(name = "appInstance", version = "add")
    @ApiDocMethod(description = "添加app实例")
    public Object addAppInstance(AppInstanceRequest appInstanceRequest) {
        AppInstanceModel appInstanceModel = new AppInstanceModel();
        BeanUtils.copyProperties(appInstanceRequest, appInstanceModel);
        return appInstanceService.insertAppInstance(appInstanceModel);
    }
}
