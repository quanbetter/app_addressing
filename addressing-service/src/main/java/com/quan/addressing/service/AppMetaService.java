package com.quan.addressing.service;

import com.quan.addressing.model.AppMetaModel;

import java.util.List;

public interface AppMetaService {
    String insertAppMeta(AppMetaModel appMetaModel);

    String deleteAppMeta(List<String> appName);

    String updateAppMeta(AppMetaModel appMetaModel);

    List<AppMetaModel> selectAppMeta(List<String> appNames);
}
