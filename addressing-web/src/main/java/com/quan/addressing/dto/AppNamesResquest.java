package com.quan.addressing.dto;

import cn.lalaframework.easyopen.doc.DataType;
import cn.lalaframework.easyopen.doc.annotation.ApiDocField;
import lombok.Data;

import java.util.List;

@Data
public class AppNamesResquest {
    @ApiDocField(description = "appNames",dataType = DataType.ARRAY,name = "appNames",example = "quan-test-one")
    private List<String> appNames;
}
