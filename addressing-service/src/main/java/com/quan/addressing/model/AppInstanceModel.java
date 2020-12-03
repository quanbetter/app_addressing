package com.quan.addressing.model;

import lombok.Data;

@Data
public class AppInstanceModel {
    private String appName;
    private Long appId;
    private String address;
    private Integer port;
}
