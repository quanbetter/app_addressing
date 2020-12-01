package com.quan.appaddressingall.common;

import com.quan.appaddressingall.entity.InterfaceInstance;

import java.util.Comparator;
import java.util.List;

public class InstanceHelper {
    public static void sortByPriority(List<InterfaceInstance> instances){
        instances.sort(new Comparator<InterfaceInstance>() {
            @Override
            public int compare(InterfaceInstance o1, InterfaceInstance o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
    }

}
