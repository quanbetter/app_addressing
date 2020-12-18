package com.quan.addressing.dto;

import cn.lalaframework.easyopen.doc.annotation.ApiDocField;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AppMetaRequest {
    @NotEmpty(message = "appName can not be empty")
    @ApiDocField(description = "appName",required = true,example = "quan-app-one")
    private String appName;

    @NotEmpty(message = "appDesc can not be empty")
    @ApiDocField(description = "app相关信息",required = false,example = "for one quan")
    private String appDesc;

    @NotEmpty(message = "appSecret can not be empty")
    @ApiDocField(description = "app秘钥",required = true,example = "2222")
    private String appSecret;
}