package com.quan.addressing.dto;

import cn.lalaframework.easyopen.doc.annotation.ApiDocField;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApiInstanceDeleteRequest {
    @NotNull(message = "appName can not be null")
    @ApiDocField(description = "appName",required = true,example = "quan-test-one")
    private String appName;

    @NotNull(message = "apiName can not be null")
    @ApiDocField(description = "apiName",required = true,example = "quan.test")
    private String apiName;
}
