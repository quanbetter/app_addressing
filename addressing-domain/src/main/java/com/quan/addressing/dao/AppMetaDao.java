package com.quan.addressing.dao;

import com.quan.addressing.entity.AppMetaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppMetaDao {
    Integer insertAppMeta(AppMetaEntity appMetaEntity);

    Integer deleteAppMeta(List<String> appName);

    Integer updateAppMeta(AppMetaEntity appMetaEntity);

    List<AppMetaEntity> selectAppMeta(List<String> appName);

    Long selectAppMetaGetId(String appName);
}
