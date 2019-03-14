package com.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureExample1 {

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do SOMething in callable");
            Thread.sleep(5000);
            return "done";
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> f = executorService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        String result = f.get();
        log.info("result: {}", result);
    }

}
