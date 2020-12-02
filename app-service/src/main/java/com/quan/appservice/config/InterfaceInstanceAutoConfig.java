package com.quan.appservice.config;

import com.quan.appservice.common.AddressingHelper;
import com.quan.appservice.common.INFInstanceResult;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.quan.appservice.common.AddressingHelper.ADDRESSING_APP_ID_URL;
import static com.quan.appservice.common.AddressingHelper.DEFAULT_APP_ID;

/**
 * Listen applicationReadyEvent and get all instance by appId
 */
@Data
@Component
@ConfigurationProperties(prefix = "unify")
public class GetInterfaceInstanceAutoConfig implements ApplicationListener<ApplicationReadyEvent> {
    Logger logger = LoggerFactory.getLogger(GetInterfaceInstanceAutoConfig.class);

    public List<String> appId = new ArrayList<>();

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (appId.isEmpty()) {
            appId.add(DEFAULT_APP_ID);
        }
        getByAppId(appId);
    }

    private void getByAppId(List<String> appId) {
        try {
            Map<String, List<INFInstanceResult>> resultMap = restTemplate.postForObject(ADDRESSING_APP_ID_URL, appId, HashMap.class);
            AddressingHelper.interfaceInstanceCache = resultMap;
            logger.info("{} all interface instance had ready ....", appId.toString());
        } catch (RestClientException e) {
            logger.warn("{} Can not get all interface instance", appId.toString());
        }
    }
}
