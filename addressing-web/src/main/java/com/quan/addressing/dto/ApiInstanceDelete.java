package com.quan.addressing.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApiInstanceDelete {
    @NotNull(message = "appName can not be null")
    private String appName;

    @NotNull(message = "apiName can not be null")
    private String apiName;
}
