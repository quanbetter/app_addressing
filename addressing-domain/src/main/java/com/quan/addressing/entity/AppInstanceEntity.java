package com.quan.addressing.entity;

import lombok.Data;

@Data
public class AppInstanceEntity {
    private String appId;
    private String address;
    private Integer port;
}
