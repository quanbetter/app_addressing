package com.quan.addressing.dao;

import com.quan.addressing.entity.AppMetaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppMetaDao {
    Integer insertAppMeta(List<AppMetaEntity> appMetaEntitys);

    Integer deleteAppMeta(List<String> appName);

    Integer updateAppMeta(AppMetaEntity appMetaEntity);

    List<AppMetaEntity> selectAppMeta(List<String> appName);
}
