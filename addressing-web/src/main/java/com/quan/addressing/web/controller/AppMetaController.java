package com.quan.addressing.web.controller;

import com.quan.addressing.dto.AppMetaRequest;
import com.quan.addressing.model.AppMetaModel;
import com.quan.addressing.service.AppMetaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/appMeta")
public class AppMetaController extends BaseController {
    @Autowired
    AppMetaService appMetaService;

    @RequestMapping("/add")
    public Object addAppMeta(@Valid @RequestBody AppMetaRequest appMetaRequest, BindingResult bindingResult) {
        if (!checkParameter(bindingResult)) {
            return jsonObject;
        }
        AppMetaModel appMetaModel = new AppMetaModel();
        BeanUtils.copyProperties(appMetaRequest, appMetaModel);
        return appMetaService.insertAppMeta(appMetaModel);
    }

    @RequestMapping("/delete")
    public String deleteAppMeta(@RequestBody List<String> appIds) {
        return appMetaService.deleteAppMeta(appIds);
    }

    @RequestMapping("/findByName")
    public List<AppMetaModel> findByName(@RequestBody List<String> appNames) {
        return appMetaService.selectAppMeta(appNames);
    }

    @RequestMapping("/update")
    public Object updateAppMeta(@Valid @RequestBody AppMetaRequest appMetaRequest, BindingResult bindingResult) {
        if (!checkParameter(bindingResult)) {
            return jsonObject;
        }
        AppMetaModel appMetaModel = new AppMetaModel();
        BeanUtils.copyProperties(appMetaRequest, appMetaModel);
        return appMetaService.updateAppMeta(appMetaModel);
    }


}
