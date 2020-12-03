package com.quan.addressing.service.impl;

import com.quan.addressing.dao.AppMetaDao;
import com.quan.addressing.entity.AppMetaEntity;
import com.quan.addressing.model.AppMetaModel;
import com.quan.addressing.service.AppMetaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppMetaServiceImpl implements AppMetaService {

    @Autowired
    AppMetaDao appMetaDao;

    @Override
    @Transactional
    public String insertAppMeta(List<AppMetaModel> appMetaModels) {
        String result = "";

        List<AppMetaEntity> appMetaEntities = appMetaModels.stream().map(appMetaModel -> {
            AppMetaEntity appMetaEntity = new AppMetaEntity();
            BeanUtils.copyProperties(appMetaModel, appMetaEntity);
            return appMetaEntity;
        }).collect(Collectors.toList());

        Integer count = 0;
        try {
            count = appMetaDao.insertAppMeta(appMetaEntities);
        } catch (Exception e) {
            if (e.getClass() == DuplicateKeyException.class) {
                result = "Some appMeta in list have already in database";
            }
        }
        if (count == appMetaEntities.size()) {
            result = "All appMeta had add";
        }
        return result;
    }

    @Override
    public String deleteAppMeta(List<String> appName) {
        Integer count = appMetaDao.deleteAppMeta(appName);
        String result = "had delete " + count + " data";
        return result;
    }

    @Override
    public void updateAppMeta(AppMetaModel appMetaModel) {
        AppMetaEntity appMetaEntity = new AppMetaEntity();
        BeanUtils.copyProperties(appMetaModel, appMetaEntity);
        appMetaDao.updateAppMeta(appMetaEntity);
    }

    @Override
    public List<AppMetaModel> selectAppMeta(List<String> appNames) {
        List<AppMetaEntity> appMetaEntities = appMetaDao.selectAppMeta(appNames);
        List<AppMetaModel> appMetaModels = appMetaEntities.stream().map(appMetaEntity -> {
            AppMetaModel appMetaModel = new AppMetaModel();
            BeanUtils.copyProperties(appMetaEntity, appMetaModel);
            return appMetaModel;
        }).collect(Collectors.toList());
        return appMetaModels;
    }
}
