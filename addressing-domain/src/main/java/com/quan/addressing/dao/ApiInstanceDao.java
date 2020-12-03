package com.quan.addressing.dao;

import com.quan.addressing.entity.ApiInstance;
import com.quan.addressing.entity.ApiInstanceEntiry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiInstanceDao {

    List<ApiInstance> selectInstanceByAppId(List<String> appIds);

    Integer insertInstance(List<ApiInstanceEntiry> instanceEntiries);
}
