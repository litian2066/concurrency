package com.concurrency.example.atomicExample;

import com.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Atomic包的演示3
 * Atomic的ABA问题
 * 比如一个线程将A更新为B然后再更新回A 其他线程操作A的时候发现A没有变化所以执行交换操作
 * 但是这不符合设计思想 所以有AotomicStamepedRefence 来对每一个值添加版本号来控制
 */
@Slf4j
@ThreadSafe
public class ConcurrencyAtomicExample4 {

    private static AtomicIntegerFieldUpdater<ConcurrencyAtomicExample4>
            updater = AtomicIntegerFieldUpdater.newUpdater(ConcurrencyAtomicExample4.class, "count");

    @Getter
    private volatile int count = 100;

    public static void main(String[] args) {
        ConcurrencyAtomicExample4 concurrencyAtomicExample4 = new ConcurrencyAtomicExample4();
        if (updater.compareAndSet(concurrencyAtomicExample4, 100, 120)) {
            log.info("update success 1, {}", concurrencyAtomicExample4.getCount());
        }
        if (updater.compareAndSet(concurrencyAtomicExample4, 100, 120)) {
            log.info("update success 2, {}", concurrencyAtomicExample4.getCount());
        } else {
            log.info("update fail, {}", concurrencyAtomicExample4.getCount());
        }
    }
}