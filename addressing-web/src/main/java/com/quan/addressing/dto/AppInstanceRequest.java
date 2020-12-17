package com.quan.addressing.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AppInstanceRequest {
    @NotEmpty(message = "appName can not be empty")
    private String appName;

    @NotEmpty(message = "address can not be empty")
    private String address;

    @NotNull(message = "port can not be empty")
    private Integer port;
}
