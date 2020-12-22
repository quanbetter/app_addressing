package com.quan.addressing.service.impl;


import com.quan.addressing.dao.AppInstanceDao;
import com.quan.addressing.dao.AppMetaDao;
import com.quan.addressing.entity.AppInstanceEntity;
import com.quan.addressing.entity.AppMetaEntity;
import com.quan.addressing.model.AppInstanceModel;
import com.quan.addressing.service.AppInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        try {
            appInstanceDao.insertAppInstance(appInstanceEntity);
        } catch (Exception e) {
            if (e.equals(DuplicateKeyException.class)) {
                return "this appInstance is already exist";
            }
            return "error";
        }
        return "done";
    }

    @Override
    public List<AppInstanceModel> selectAppInstance(String appName) {
        Long appId = appMetaDao.selectAppMetaGetId(appName);
        if (Objects.nonNull(appId) && appId != 0) {
            List<AppInstanceEntity> appInstances = appInstanceDao.selectAppInstance(appId);
            List<AppInstanceModel> appInstanceModels = appInstances.stream().map(appInstanceEntity -> {
                AppInstanceModel appInstanceModel = new AppInstanceModel();
                BeanUtils.copyProperties(appInstanceEntity, appInstanceModel);
                return appInstanceModel;
            }).collect(Collectors.toList());
            return appInstanceModels;
        }
        return Collections.emptyList();
    }

    @Override
    public Integer deleteAppInstance(String appName) {
        Long appId = appMetaDao.selectAppMetaGetId(appName);
        if (Objects.nonNull(appId) && appId != 0) {
            return appInstanceDao.deleteAppInstance(appId);
        }
        return 0;
    }
}
