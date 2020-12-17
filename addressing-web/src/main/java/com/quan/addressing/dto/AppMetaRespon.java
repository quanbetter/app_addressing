package com.quan.addressing.dto;

import com.quan.addressing.common.BaseResult;
import com.quan.addressing.model.AppMetaModel;
import lombok.Data;

import java.util.List;

@Data
public class AppMetaRespon extends BaseResult {
    private List<AppMetaModel> data;
}
