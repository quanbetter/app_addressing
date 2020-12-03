package com.quan.addressing.dao;

import com.quan.addressing.entity.AppInstanceEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface AppInstanceDao {
    Integer insertAppInstance(AppInstanceEntity appInstanceEntity);
}
