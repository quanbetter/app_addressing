package com.quan.addressing.model;

import lombok.Data;

@Data
public class ApiInstanceModel {
    private String appName;
    private Long appId;
    private String interfaceName;
    private String path;
    private Long priority;
}
