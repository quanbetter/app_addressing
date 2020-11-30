package com.quan.appservice.controller;

import com.quan.appservice.dto.LaborerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.quan.appservice.common.AddressingHelper.HTTP_START;
import static com.quan.appservice.common.AddressingHelper.SLASH;
import static com.quan.appservice.common.AddressingHelper.SPLIT_KEY_NAME;
import static com.quan.appservice.common.AddressingHelper.interfaceInstance;

@RestController
@RequestMapping("/unify")
public class HeelController {
    Logger logger = LoggerFactory.getLogger(HeelController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/login")
    public Boolean Forword(@RequestParam("inf_name") String interfaceName, @RequestParam("app_key") String appKey, @RequestBody LaborerDTO laborerDTO) {
        logger.info("login{}",laborerDTO.toString());
        String keyName = appKey + SPLIT_KEY_NAME + interfaceName;
        List<String> addressList = interfaceInstance.get(keyName);
        Boolean result = false;
        if (addressList != null) {
            try {
                result = restTemplate.postForObject(HTTP_START + addressList.get(0) + SLASH + interfaceName, laborerDTO, Boolean.class);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    //随机挑选
    private String pickAddress(List<String> addressList) {
        return null;
    }

}
