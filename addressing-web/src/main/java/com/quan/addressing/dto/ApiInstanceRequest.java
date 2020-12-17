package com.quan.addressing.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ApiInstanceRequest {
    @NotNull(message = "AppId can not be null")
    private String appName;

    @NotNull(message = "Api name can not be empty")
    private String interfaceName;

    @NotNull(message = "Path can not be empty")
    private String path;

    @NotNull(message = "Priority can not be null")
    private Long priority;
}
