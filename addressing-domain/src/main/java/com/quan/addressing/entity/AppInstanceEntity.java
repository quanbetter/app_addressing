package com.quan.addressing.entity;

import lombok.Data;

@Data
public class AppInstanceEntity {
    private Long id;
    private Long appId;
    private String address;
    private Integer port;
}
