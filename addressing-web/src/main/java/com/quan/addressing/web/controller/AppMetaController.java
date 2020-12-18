package com.quan.addressing.web.controller;

import cn.lalaframework.easyopen.annotation.Api;
import cn.lalaframework.easyopen.annotation.ApiService;
import cn.lalaframework.easyopen.doc.annotation.ApiDoc;
import cn.lalaframework.easyopen.doc.annotation.ApiDocMethod;
import com.quan.addressing.dto.AppMetaRequest;
import com.quan.addressing.dto.AppNamesResquest;
import com.quan.addressing.model.AppMetaModel;
import com.quan.addressing.service.AppMetaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@ApiService
@ApiDoc("app元数据模块")
public class AppMetaController {
    @Autowired
    AppMetaService appMetaService;

    @Api(name = "appMeta", version = "add")
    @ApiDocMethod(description = "添加App元数据")
    public Object addAppMeta(@Valid @RequestBody AppMetaRequest appMetaRequest) {
        AppMetaModel appMetaModel = new AppMetaModel();
        BeanUtils.copyProperties(appMetaRequest, appMetaModel);
        return appMetaService.insertAppMeta(appMetaModel);
    }

    @Api(name = "appMeta", version = "delete")
    @ApiDocMethod(description = "删除App元数据")
    public String deleteAppMeta(@RequestBody AppNamesResquest request) {
        return appMetaService.deleteAppMeta(request.getAppNames());
    }

    @Api(name = "appMeta", version = "findByName")
    @ApiDocMethod(description = "按照AppName获取App元数据")
    public List<AppMetaModel> findByName(@RequestBody AppNamesResquest request) {
        return appMetaService.selectAppMeta(request.getAppNames());
    }

    @Api(name = "appMeta", version = "update")
    @ApiDocMethod(description = "跟新App元数据")
    public Object updateAppMeta(@RequestBody AppMetaRequest appMetaRequest) {
        AppMetaModel appMetaModel = new AppMetaModel();
        BeanUtils.copyProperties(appMetaRequest, appMetaModel);
        return appMetaService.updateAppMeta(appMetaModel);
    }
}
