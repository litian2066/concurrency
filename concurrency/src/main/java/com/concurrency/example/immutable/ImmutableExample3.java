package com.concurrency.example.immutable;

import com.concurrency.annotations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * Guava Immutable...演示
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3); // 最高初始化12个值 超过要扩容

    private final static ImmutableSet<Integer> set = ImmutableSet.copyOf(list); // 最高初始化12个值 超过要扩容

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4); // 初始化的值成对出现

    // 更好用的方式
    private final static ImmutableMap<Integer, Integer> map2 =
            ImmutableMap.<Integer, Integer>builder().put(1, 3).put(4, 9).build();

    public static void main(String[] args) {
        // list.add(2);
        /*
            java.lang.UnsupportedOperationException
         */
        map.put(2, 45);
        map2.put(2, 45);
    }
}
