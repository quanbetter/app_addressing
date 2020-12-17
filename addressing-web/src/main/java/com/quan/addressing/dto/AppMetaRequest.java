package com.quan.addressing.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AppMetaRequest {
    @NotEmpty(message = "appName can not be empty")
    private String appName;

    @NotEmpty(message = "appDesc can not be empty")
    private String appDesc;

    @NotEmpty(message = "appSecret can not be empty")
    private String appSecret;
}
