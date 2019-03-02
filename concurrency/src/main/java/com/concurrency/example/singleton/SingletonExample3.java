package com.concurrency.example.singleton;

import com.concurrency.annotations.NotRecommend;
import com.concurrency.annotations.NotThreadSafe;
import com.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉式单例模式
 *  第一次调用的时候初始化
 *  不推荐使用 性能开销比较大
 */
@Slf4j
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    private SingletonExample3() {

    }

    private static SingletonExample3 singletonExample1 = null;

    public static synchronized SingletonExample3 getInstance() {
        if (singletonExample1 == null) {
            singletonExample1 = new SingletonExample3();
        }
        return singletonExample1;
    }
}
