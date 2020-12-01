package com.quan.appaddressingall.entity;

import lombok.Data;

@Data
public class InterfaceInstance {
    private Long id;
    private String interfaceName;
    private String address;
    private Integer port;
    private String appId;
    private boolean isDefault;
    private String keyName;
    private Long priority;
}
