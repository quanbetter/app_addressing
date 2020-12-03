package com.quan.addressing.service;

import com.quan.addressing.entity.AppMetaEntity;
import com.quan.addressing.model.AppMetaModel;

import java.util.List;

public interface AppMetaService {
    String insertAppMeta(List<AppMetaModel> appMetaModels);

    String deleteAppMeta(List<String> appName);

    void updateAppMeta(AppMetaModel appMetaModel);

    List<AppMetaModel> selectAppMeta(List<String> appNames);
}
