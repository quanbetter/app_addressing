package com.quan.addressing.web;

import cn.lalaframework.easyopen.ApiConfig;
import cn.lalaframework.easyopen.support.ApiController;
import cn.lalaframework.exception.ServiceException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addressing")
public class IndexController extends ApiController {

    @Override
    protected void initApiConfig(ApiConfig apiConfig) {
        apiConfig.setShowDoc(true);
        apiConfig.setAppName("quan-addressing");
        apiConfig.setShowLogger(true);
        apiConfig.setIgnoreValidate(false);
        apiConfig.setIgnoreAppkeyValidate(true);
        apiConfig.setRetIntMode(true);
        apiConfig.setShowLogger(true);
        apiConfig.setShowMonitor(true);
        apiConfig.setStandardMode(false);
        apiConfig.setDataName("data");
        apiConfig.setServiceExceptionClass(ServiceException.class);
        apiConfig.setJsonResultSerializerPropertyNamingStrategy(PropertyNamingStrategy.CamelCase);
    }
}
