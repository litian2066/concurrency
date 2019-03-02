package com.concurrency.example.immutable;
/**

 不可变对象:
     对象创建以后其状态就不能修改
     对象所有域都是final类型
     对象是正确创建的(在对象创建期间, this引用没有逸出)
 final关键字: 类,方法,变量
     修饰类: 不能被继承 所有成员方法都被隐式的声明为final方法
     修饰方法: 1.锁定方法不能被继承类修改; 2.效率(早期内嵌)
        一个类的private方法会被隐式为final方法
     修饰变量: 基本数据类型变量, 引用类型变量
 // java提供的不可变的类 底层都是抛出异常
    Collections.unmodifiableXX
    Guava: ImmutableXXX
 **/