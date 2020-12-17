package com.quan.addressing.util;


import com.quan.addressing.entity.ApiInstanceSelectResult;

import java.util.Comparator;
import java.util.List;

public class InstanceHelper {
    public static List<ApiInstanceSelectResult> sortByPriority(List<ApiInstanceSelectResult> instances) {
        instances.sort(new Comparator<ApiInstanceSelectResult>() {
            @Override
            public int compare(ApiInstanceSelectResult o1, ApiInstanceSelectResult o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
        return instances;
    }
}
