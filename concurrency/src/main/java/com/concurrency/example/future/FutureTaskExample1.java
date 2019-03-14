package com.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskExample1 {

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do SOMething in callable");
            Thread.sleep(5000);
            return "done";
        }
    }

    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do SOMething in callable");
                Thread.sleep(5000);
                return "done";
            }
        });

        String result = "111";
        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            log.info("do SOMething in runable");
        }, result);
        /*new Callable<String>(() -> {
            {
                public String call() throws Exception {
                return null;
            }
            };
        }); */

        new Thread(futureTask2).start();
        log.info("do something in main");
        Thread.sleep(1000);
        String s = futureTask2.get();
        log.info("{}", s);
    }

}
