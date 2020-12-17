package com.quan.addressing.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.validation.BindingResult;

public class BaseController {
    public JSONObject jsonObject = new JSONObject();

    public Boolean checkParameter(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            jsonObject.put("ret", 1);
            jsonObject.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return false;
        }
        return true;
    }

}
