package com.quan.addressing.entity;

import lombok.Data;

@Data
public class AppMetaEntity {
    private Long id;
    private String appName;
    private String appDesc;
    private String appSecret;
}
