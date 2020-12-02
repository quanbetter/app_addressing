package com.quan.appaddressingall.dao;

import com.quan.appaddressingall.entity.INFInstanceResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppInstanceDao {
    List<INFInstanceResult> getDefaultAppInstance();
}
