package com.quan.appservice.common;

import lombok.Data;

@Data
public class INFInstanceResult {
    private String keyNameVersion = "default";
    private String path = "/";
    private String address;
    private Long port;
    private String appSecret;
}
