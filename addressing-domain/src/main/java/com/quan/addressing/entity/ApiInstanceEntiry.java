package com.quan.addressing.entity;

import lombok.Data;

@Data
public class ApiInstanceEntiry {
    private Long id;
    private Long appId;
    private String interfaceName;
    private String path;
    private Long priority;
}
