package com.code.anli.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName practice
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/24 17:18
 * @Version 1.0
 **/
public class Practice {

    public static List<Emp> list = new ArrayList<>();

    static {
        list.add(new Emp("上海", "小名", 17));
        list.add(new Emp("北京", "小红", 18));
        list.add(new Emp("深圳", "小蓝", 19));
        list.add(new Emp("广州", "小灰", 20));
        list.add(new Emp("杭州", "小黄", 21));
        list.add(new Emp("贵阳", "小白", 22));
    }


    @Test
    public void hot() {
        Map<String, Integer> collect = list.stream().collect(Collectors.toMap(Emp::getName, Emp::getAge));
        collect.forEach((s, integer) -> System.out.println(s+integer));


    }

}


class Emp {
    private String city;
    private String name;
    private Integer age;

    public Emp(String city, String name, Integer sequence) {
        this.city = city;
        this.name = name;
        this.age = sequence;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

