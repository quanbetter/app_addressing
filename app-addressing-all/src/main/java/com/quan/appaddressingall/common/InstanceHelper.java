package com.quan.appaddressingall.common;

import com.quan.appaddressingall.entity.APIInstance;

import java.util.Comparator;
import java.util.List;

public class InstanceHelper {
    public static List<APIInstance> sortByPriority(List<APIInstance> instances) {
        instances.sort(new Comparator<APIInstance>() {
            @Override
            public int compare(APIInstance o1, APIInstance o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
        return instances;
    }
}
