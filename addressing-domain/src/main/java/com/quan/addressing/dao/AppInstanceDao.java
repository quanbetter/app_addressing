package com.quan.addressing.dao;

import com.quan.addressing.entity.AppInstanceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppInstanceDao {
    Integer insertAppInstance(AppInstanceEntity appInstanceEntity);
    Integer deleteAppInstance(Long appId);
    Integer updateAppInstance(AppInstanceEntity appInstanceEntity);
    List<AppInstanceEntity> selectAppInstance(Long appId);
}
