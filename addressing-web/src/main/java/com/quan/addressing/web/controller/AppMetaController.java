package com.quan.addressing.web.controller;

import com.quan.addressing.model.AppMetaModel;
import com.quan.addressing.service.AppMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appMeta")
public class AppMetaController {
    @Autowired
    AppMetaService appMetaService;

    @RequestMapping("/add")
    public String addAppMeta(@RequestBody List<AppMetaModel> metaModels){
        return appMetaService.insertAppMeta(metaModels);
    }

    @RequestMapping("/delete")
    public String deleteAppMeta(@RequestBody List<String>  appIds){
        return appMetaService.deleteAppMeta(appIds);
    }

    @RequestMapping("/findByName")
    public List<AppMetaModel> findByName(@RequestBody List<String>  appNames){
        return appMetaService.selectAppMeta(appNames);
    }

    @RequestMapping("/update")
    public String updateAppMeta(@RequestBody AppMetaModel appMetaModel){
        return appMetaService.updateAppMeta(appMetaModel);
    }


}
