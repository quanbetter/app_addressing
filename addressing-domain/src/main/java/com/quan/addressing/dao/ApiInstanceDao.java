package com.quan.addressing.dao;

import com.quan.addressing.entity.ApiInstanceSelectResult;
import com.quan.addressing.entity.ApiInstanceEntiry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiInstanceDao {

    List<ApiInstanceSelectResult> selectInstanceByAppName(List<String> appName);

    Integer insertInstance(ApiInstanceEntiry instanceEntiries);

    Integer updateInstance(ApiInstanceEntiry apiInstanceEntiry);

    Integer deleteInstance(Long appId, String apiName);

}
