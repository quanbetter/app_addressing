package com.quan.appservice.common;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AddressingHelper {
    public static final String ADDRESSING_URL = "http://127.0.0.1:8888/interface/getAllInstance";
    public static final String COLON = ":";
    public static final String SLASH = "/";
    public static final String SPLIT_KEY_NAME = "&";
    public static final String HTTP_START = "http://";
    public static Map<String, List<String>> interfaceInstance;
}
