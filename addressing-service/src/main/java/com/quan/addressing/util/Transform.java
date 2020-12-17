package com.quan.addressing.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Transform {
    public static <R>  List<R> forList(List<?> list, Class<R> rClass){
        return list.stream().map(o -> {
            R  r= null;
            try {
                r = (R) rClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(o,r);
            return r;
        }).collect(Collectors.toList());
    }
}
