package com.quan.addressing.dto;

import com.quan.addressing.common.BaseResult;
import com.quan.addressing.model.ApiInstanceResult;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ApiInstanceRespon extends BaseResult {
    private Map<String, List<ApiInstanceResult>> data;
}
