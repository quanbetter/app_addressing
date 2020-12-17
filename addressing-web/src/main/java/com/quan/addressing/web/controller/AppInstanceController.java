package com.quan.addressing.web.controller;

import com.quan.addressing.dto.AppInstanceRequest;
import com.quan.addressing.model.AppInstanceModel;
import com.quan.addressing.service.AppInstanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/appInstance")
public class AppInstanceController extends BaseController {
    @Autowired
    AppInstanceService appInstanceService;

    @RequestMapping("/add")
    public Object addAppInstance(@Valid @RequestBody AppInstanceRequest appInstanceRequest, BindingResult bindingResult) {
       if (!checkParameter(bindingResult)){
           return jsonObject;
       }
        AppInstanceModel appInstanceModel = new AppInstanceModel();
        BeanUtils.copyProperties(appInstanceRequest,appInstanceModel);
        return appInstanceService.insertAppInstance(appInstanceModel);
    }
}
