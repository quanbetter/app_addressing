package com.quan.appservice.config;

import com.quan.appservice.common.AddressingHelper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.quan.appservice.common.AddressingHelper.ADDRESSING_ALL_URL;
import static com.quan.appservice.common.AddressingHelper.ADDRESSING_APP_ID_URL;

/**
 * Listen applicationReadyEvent and get all instance by appId
 */
@Data
@Component
@ConfigurationProperties(prefix = "unify")
public class GetInterfaceInstanceAutoConfig implements ApplicationListener<ApplicationReadyEvent> {
    Logger logger = LoggerFactory.getLogger(GetInterfaceInstanceAutoConfig.class);

    public List<String> appId;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
      if (appId != null & !appId.isEmpty()){
          getByAppId(appId);
      }else {
          getAll();
      }
    }

    private void getAll(){
        try{
            Map<String, List<String>> resultMap = restTemplate.postForObject(ADDRESSING_ALL_URL, null, HashMap.class);
            AddressingHelper.interfaceInstanceCache = resultMap;
            logger.info("All interface instance had ready ....");
        }catch (RestClientException e){
            logger.warn("Can not get all interface instance");
        }
    }

    private void getByAppId(List<String> appId){
        try{
            Map<String, List<String>> resultMap = restTemplate.postForObject(ADDRESSING_APP_ID_URL, appId, HashMap.class);
            AddressingHelper.interfaceInstanceCache = resultMap;
            logger.info("{} all interface instance had ready ....",appId.toString());
        }catch (RestClientException e){
            logger.warn("{} Can not get all interface instance",appId.toString());
        }
    }
}
