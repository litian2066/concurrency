package com.concurrency.example.lock;
/**
    Synchronized是JVM层面的锁 所以在执行的时候出现异常 JVM会自动解除锁定
    ReentrantLock ReentrantReadWriteLock StampedLock这些锁是对象层面的锁 说白了就是自己写的锁
    可以查看源码 但是锁定必须伴随这解锁 所以时常定义在finally里面才安全些
    StampedLock对吞吐量有很大的改进 特别是在读的操作比较多的情况下 复杂的API

    总结:
    1.当只有少量竞争者 SYNC是个很好的实现
    2.竞争者不少, 但是线程增长的趋势是能够预估的 ReentrantLock是一个很好的实现
    适合自己的使用场景的锁是最好的 对了 除了SYCN 其他的如果使用不当是会造成死锁的

 **/