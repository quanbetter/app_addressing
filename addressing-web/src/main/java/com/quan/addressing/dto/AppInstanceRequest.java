package com.quan.addressing.dto;

import cn.lalaframework.easyopen.doc.annotation.ApiDocField;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AppInstanceRequest {
    @NotEmpty(message = "appName can not be empty")
    @ApiDocField(description = "appName", required = true, example = "quan-app-three")
    private String appName;

    @NotEmpty(message = "address can not be empty")
    @ApiDocField(description = "address", required = true, example = "127.0.1.90")
    private String address;

    @NotNull(message = "port can not be empty")
    @ApiDocField(description = "port",required = true,example = "9002")
    private Integer port;
}