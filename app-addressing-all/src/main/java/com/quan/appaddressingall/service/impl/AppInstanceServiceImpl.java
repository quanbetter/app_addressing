package com.quan.appaddressingall.service.impl;

import com.quan.appaddressingall.dao.AppInstanceDao;
import com.quan.appaddressingall.entity.APIInstanceResult;
import com.quan.appaddressingall.service.AppInstanceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AppInstanceServiceImpl implements AppInstanceService {

    @Autowired
    AppInstanceDao appInstanceDao;

    @Override
    public List<APIInstanceResult> getDefaultAppInstance() {
       return appInstanceDao.getDefaultAppInstance();
    }
}
