package com.quan.appaddressingall.entity;

import lombok.Data;

@Data
public class AppIdMeta {
    private Integer id;
    private String appId;
    private Long   appKey;
    private String secret;
}
