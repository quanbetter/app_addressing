package com.quan.appaddressingall.dao;

import com.quan.appaddressingall.entity.APIInstanceResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppInstanceDao {
    List<APIInstanceResult> getDefaultAppInstance();
}
