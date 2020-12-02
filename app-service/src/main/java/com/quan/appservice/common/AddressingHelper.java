package com.quan.appservice.common;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AddressingHelper {
    public static final String ADDRESSING_ALL_URL = "http://127.0.0.1:8888/interface/getAllInstance";
    public static final String ADDRESSING_APP_ID_URL = "http://127.0.0.1:8888/interface/getInstanceByAppId";
    public static final String COLON = ":";
    public static final String SLASH = "/";
    public static final String SPLIT_KEY_NAME = "&";
    public static final String HTTP_HEAD = "http://";
    public static final String HTTPS_HEAD = "http://";
    public static Map<String, List<INFInstanceResult>> interfaceInstanceCache;
}
