package com.concurrency.example.commonUnSafe;

import com.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 实际项目中使用JodaTime来格式化时间
 * 不仅是线程安全 而且还有很多优势 (不仅仅是加了Sync关键字  )
 * 推荐使用
 */
@Slf4j
@ThreadSafe
public class DateFornatExample2 {

    // 请求总数
    private static Integer threadTotal = 5000;

    // 同时执行请求总数
    private static Integer clientTotal = 200;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    public static void main(String[] args) throws  Exception{

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量 允许同时执行请求200个
        final Semaphore semaphore = new Semaphore(clientTotal); // 允许并发数
        // 定义闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);

        for (int i = 0; i < threadTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    // 允许线程访问
                    semaphore.acquire(); // 当前线程是否允许被执行
                    doSomeThing(count);
                    semaphore.release(); // // 释放线程
                } catch (Exception e) {
                    log.error("error: ", e);
                }
                countDownLatch.countDown();
            });

        }

        countDownLatch.await();
        executorService.shutdown();
        // log.info("result: {}", stringBuffer.length());
    }

    private static void doSomeThing(int i) {
        log.info("{}, {}",i, DateTime.parse("20180208", dateTimeFormatter).toDate());
    }


}
