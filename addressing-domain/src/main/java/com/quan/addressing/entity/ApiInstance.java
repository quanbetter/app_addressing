package com.quan.addressing.entity;

import lombok.Data;

@Data
public class ApiInstance {
    private String appId;
    private String appSecret;
    private String interfaceName;
    private String path;
    private String keyName;
    private Long priority;
    private String address;
    private Long port;
}
