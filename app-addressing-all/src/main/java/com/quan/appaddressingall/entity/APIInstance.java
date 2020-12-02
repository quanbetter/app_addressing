package com.quan.appaddressingall.entity;

import lombok.Data;

@Data
public class APIInstance {
    private String appId;
    private String interfaceName;
    private String keyNameVersion;
    private String path;
    private String version;
    private String address;
    private Long port;
    private Long priority;
    private String appSecret;
}
