package com.quan.addressing.model;

import lombok.Data;

@Data
public class ApiInstanceResult {
    private String path = "/";
    private String address;
    private Long port;
    private String appSecret;
}
