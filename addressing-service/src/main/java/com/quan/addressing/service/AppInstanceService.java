package com.quan.addressing.service;


import com.quan.addressing.model.AppInstanceModel;

import java.util.List;


public interface AppInstanceService {

    String insertAppInstance(AppInstanceModel appInstanceModel);

    List<AppInstanceModel> selectAppInstance(String appName);

    Integer deleteAppInstance(String appName);
}
