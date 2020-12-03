package com.quan.addressing.util;


import com.quan.addressing.entity.ApiInstance;

import java.util.Comparator;
import java.util.List;

public class InstanceHelper {
    public static List<ApiInstance> sortByPriority(List<ApiInstance> instances) {
        instances.sort(new Comparator<ApiInstance>() {
            @Override
            public int compare(ApiInstance o1, ApiInstance o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
        return instances;
    }
}
