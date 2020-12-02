package com.quan.appaddressingall.service.impl;

import com.quan.appaddressingall.dao.AppInstanceDao;
import com.quan.appaddressingall.entity.APIInstanceResult;
import com.quan.appaddressingall.service.AppInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppInstanceServiceImpl implements AppInstanceService {

    @Autowired
    AppInstanceDao appInstanceDao;

    @Override
    public Map<String, List<APIInstanceResult>> getDefaultAppInstance() {
        Map<String, List<APIInstanceResult>> resultMap = new HashMap<>();
        List<APIInstanceResult> appAPIInstanceResults = appInstanceDao.getDefaultAppInstance();
        resultMap.put("default", appAPIInstanceResults);
        return resultMap;
    }
}
