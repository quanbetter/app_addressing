package com.quan.addressing.common;

import lombok.Data;

@Data
public class BaseResult {
    private String msg = "success";
    private Integer ret  = 0;
}
