package com.quan.appservice.config;

import com.quan.appservice.common.AddressingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.quan.appservice.common.AddressingHelper.ADDRESSING_URL;

@Component
public class GetInterfaceInstanceAutoConfig implements ApplicationListener<ApplicationReadyEvent> {
    Logger logger = LoggerFactory.getLogger(GetInterfaceInstanceAutoConfig.class);

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try{
            Map<String, List<String>> resultMap = restTemplate.postForObject(ADDRESSING_URL, null, HashMap.class);
            AddressingHelper.interfaceInstance = resultMap;
            logger.info("Interface instance had ready ....");
        }catch (RestClientException e){
            logger.warn("Can not get interface instance");
        }
    }
}
