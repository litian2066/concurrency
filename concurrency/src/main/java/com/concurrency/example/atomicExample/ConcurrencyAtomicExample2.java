package com.concurrency.example.atomicExample;

import com.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Atomic包的演示
 */
@Slf4j
@ThreadSafe
public class ConcurrencyAtomicExample2 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    // private static AtomicLong count = new AtomicLong(0);
    // Jdk8新增的类
    /**
     * CAS是在一个死循环内,在大量修改失败的情况下 这些原子操作就会进行大量尝试,会对性能有影响
     * 对于double long JVM允许将一个64位操作拆成2个32位操作
     * 思想:
     *  将略点数据分离  将内部核心数据value分离成一个数组 每个线程访问的时候通过hash算法分别处理对应的部分 结果为各个分离的值的合计 通过分散提高性能
     *  相当于在atomicLong的基础上将单点的更新压力分散到各个节点上 在低并发的时候通过对Base的直接更新 可以很好的保障和atomic一样的性能
     *  缺点: 统计的时候如果有并发更新可能会导致结果有误差
     *  实际上处理高并发数据的时候可以优先使用longAddr而不是继续使用atomicLong
     *  在线程竞争很低的时候用atomicLong更好 还有在统计序列号的时候 需要正确的数值 全局唯一的atomicLong是最好的选择
     *
     */
    private static LongAdder count = new LongAdder();

    public static void main(String[] args) throws Exception{
        // 初始化线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量 十字路口同时让多少汽车通过
        final Semaphore semaphore = new Semaphore(threadTotal); // 允许并发数
        // 定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); // 希望执行完后统计总数 这儿传入请求总数
        for (int i = 0; i < clientTotal; i++) {
            // 放入请求
            executorService.execute(() -> {
                try {
                    semaphore.acquire(); // 当前线程是否允许被执行
                    add();
                    semaphore.release(); // 释放线程
                } catch (Exception e) {
                    log.error("Exception", e);
                }
                countDownLatch.countDown(); // 计数
            });
        }
        countDownLatch.await(); // 保证countDown减为0
        executorService.shutdown();
        log.info("count: {}", count); // 5000
    }

    private static void add() {
        // count.incrementAndGet(); // 先增加然后获取当前的值
        // count.getAndIncrement();
        count.increment();
    }
}
