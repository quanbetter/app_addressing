package com.quan.addressing.service.impl;


import com.quan.addressing.dao.AppInstanceDao;
import com.quan.addressing.dao.AppMetaDao;
import com.quan.addressing.entity.AppInstanceEntity;
import com.quan.addressing.entity.AppMetaEntity;
import com.quan.addressing.model.AppInstanceModel;
import com.quan.addressing.service.AppInstanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppInstanceServiceImpl implements AppInstanceService {

    @Autowired
    AppInstanceDao appInstanceDao;

    @Autowired
    AppMetaDao appMetaDao;

    @Override
    @Transactional
    public String insertAppInstance(AppInstanceModel appInstanceModel) {
        List<String> appNames = new ArrayList<>();
        appNames.add(appInstanceModel.getAppName());
        List<AppMetaEntity> appMetaEntities = appMetaDao.selectAppMeta(appNames);
        if (appMetaEntities.size() == 0) {
            return appInstanceModel.getAppName() + "  isn't in appMeta, please add first";
        }
        AppInstanceEntity appInstanceEntity = new AppInstanceEntity();
        BeanUtils.copyProperties(appInstanceModel, appInstanceEntity);
        appInstanceEntity.setAppId(appMetaEntities.get(0).getId());
        appInstanceDao.insertAppInstance(appInstanceEntity);
        return "done";
    }
}
