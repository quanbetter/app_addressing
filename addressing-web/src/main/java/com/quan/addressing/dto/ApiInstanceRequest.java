package com.quan.addressing.dto;

import cn.lalaframework.easyopen.doc.annotation.ApiDocField;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ApiInstanceRequest {
    @NotNull(message = "AppId can not be null")
    @ApiDocField(description = "appName",required = true,example = "quan-quan")
    private String appName;

    @NotNull(message = "Api name can not be empty")
    @ApiDocField(description = "interfaceName",required = true,example ="test99" )
    private String interfaceName;

    @NotNull(message = "Path can not be empty")
    @ApiDocField(description = "path",required = true,example = "/quan/test99")
    private String path;

    @NotNull(message = "Priority can not be null")
    @ApiDocField(description = "priority",required = false,example = "1")
    private Long priority;
}
