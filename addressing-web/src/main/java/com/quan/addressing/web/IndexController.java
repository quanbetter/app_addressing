package com.quan.addressing.web;

import cn.lalaframework.easyopen.ApiConfig;
import cn.lalaframework.easyopen.interceptor.ApiInterceptor;
import cn.lalaframework.easyopen.limit.ApiLimitManager;
import cn.lalaframework.easyopen.support.ApiController;
import cn.lalaframework.easyopen.support.LimitInterceptor;
import cn.lalaframework.exception.ServiceException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 接口文档：http://localhost:8888/addressing/doc
 */
@Controller
@RequestMapping("/addressing")
public class IndexController extends ApiController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void initApiConfig(ApiConfig apiConfig) {
        apiConfig.setShowDoc(true);//是否展示文档
        apiConfig.setAppName("quan-addressing");
        apiConfig.setShowLogger(true);//是否打日志
        apiConfig.setIgnoreValidate(false);//不忽略接口验证
        apiConfig.setIgnoreAppkeyValidate(true);//忽略APPId接口验证
        apiConfig.setRetIntMode(true);
        apiConfig.setShowMonitor(true);
        apiConfig.setStandardMode(false);//非标准模式
        apiConfig.setDataName("data");
        apiConfig.setServiceExceptionClass(ServiceException.class);
        apiConfig.setJsonResultSerializerPropertyNamingStrategy(PropertyNamingStrategy.CamelCase);

        apiConfig.setInterceptors(new ApiInterceptor[]{
                new LimitInterceptor()
        });

        apiConfig.setLimitManager(new ApiLimitManager(redisTemplate));
    }
}
