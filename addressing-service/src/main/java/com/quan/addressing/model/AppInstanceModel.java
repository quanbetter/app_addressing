package com.quan.addressing.model;

import lombok.Data;

@Data
public class AppInstanceModel {
    private String appId;
    private String appName;
    private String address;
    private Integer port;
}
