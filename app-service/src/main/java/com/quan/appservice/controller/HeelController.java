package com.quan.appservice.controller;

import com.quan.appservice.dto.LaborerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.quan.appservice.common.AddressingHelper.HTTP_HEAD;
import static com.quan.appservice.common.AddressingHelper.SLASH;
import static com.quan.appservice.common.AddressingHelper.SPLIT_KEY_NAME;
import static com.quan.appservice.common.AddressingHelper.interfaceInstanceCache;

@RestController
@RequestMapping("/unify")
public class HeelController {
    Logger logger = LoggerFactory.getLogger(HeelController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/login")
    public Boolean Forword(@RequestParam("inf_name") String interfaceName, @RequestParam("app_key") String appKey, @RequestBody LaborerDTO laborerDTO) {
        logger.info("login{}", laborerDTO.toString());
        String keyName = appKey + SPLIT_KEY_NAME + interfaceName;
        List<String> addressList = interfaceInstanceCache.get(keyName);
        Boolean result = false;
        if (addressList != null) {
            for (String addrPost : addressList) {
                try {
                    result = restTemplate.postForObject(HTTP_HEAD + addrPost + SLASH + interfaceName, laborerDTO, Boolean.class);
                    break;
                } catch (RestClientResponseException e) {
                    if (500 <= e.getRawStatusCode()) {
                        continue;
                    }
                }
            }
        }
        return result;
    }

    //随机挑选
    private String pickAddress(List<String> addressList) {
        return null;
    }

}
