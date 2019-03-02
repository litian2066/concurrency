package com.concurrency.example.immutable;

import com.concurrency.annotations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        // map = new HashMap<>(); 不允许指向新的对象
        // 但是可以修改值, 于是Java提供了一系列不能修改的集合
        map.put(1, 3); // 所以容易引发安全问题
        log.info("{}", map.get(1)); // 3
    }

    private void test(final int a) {
        // a = 1; 不能修改传入的final类型变量
    }
}
