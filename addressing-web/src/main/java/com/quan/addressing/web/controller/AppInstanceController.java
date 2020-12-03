package com.quan.addressing.web.controller;

import com.quan.addressing.model.AppInstanceModel;
import com.quan.addressing.service.AppInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appInstance")
public class AppInstanceController {
    @Autowired
    AppInstanceService appInstanceService;

    @RequestMapping("/add")
    public String addAppInstance(@RequestBody AppInstanceModel appInstanceModel){
        return appInstanceService.insertAppInstance(appInstanceModel);
    }
}
