package com.concurrency.example.sync;
/**
 原子性:
    除了atomic包能提供互斥性/原子性  还有锁
    synchronized: 依赖JVM
        修饰代码块: 大括号括起来的代码,作用于调用的对象
        修饰方法: 整个方法,作用于调用的对象
        修饰静态方法: 整个静态方法,作用于所有的对象
        修饰类: 括号括起来的部分,作用于所有对象
    Lock: 依赖特殊的CPU指令, 代码实现, ReentrantLock
 Lock
 原子性对比:
    synchronized: 不可中断锁, 适合竞争不激烈, 可读性好
    Lock: 可中断锁, 多样化同步, 竞争激烈时能维持常态
    Atomic: 竞争激烈时能维持常态, 比Lock性能好, 只能同步一个值
 可见性:
    导致共享变量在线程间不可见的原因:
        线程交叉执行
        重排序结合线程交叉执行
        共享变量更新后的值没有在工作内存与主内存间及时更新
    JMM关于synchronized的两条规定:
        线程解锁前, 必须把共享变量的最新值刷新到主内存中
        线程加锁前, 将清空工作内存中的共享变量的值, 从而使用共享变量时需要从主内存中重新读取最新的
        加锁和解锁是同一把锁
    volatile
        通过加入内存屏障和禁止重排序优化来实现
            1.对volatile变量写操作时, 会在写操作后加入一条store屏障指令, 将本地内存中的共享变量刷新到主内存中
            2.对volatile变量读操作时,会在读操作之前加入一条load屏障指令, 从主内存中读取共享变量
        可以作为状态标识量
 volatile boolean inited = false;
 // 线程一
    context = loadContext();
    inited = true;
 // 线程二:
    while(!inited) {
        sleep()
    }
    doSomeThing(context)

*/