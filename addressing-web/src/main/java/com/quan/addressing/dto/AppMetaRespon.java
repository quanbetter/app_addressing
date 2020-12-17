package com.quan.addressing.dto;

import com.quan.addressing.model.AppMetaModel;
import lombok.Data;

import java.util.List;

@Data
public class AppMetaRespon{
    private List<AppMetaModel> data;
}
