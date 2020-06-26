package xyz.gabear.learn.concurrency.one.example.singleton;

import xyz.gabear.learn.concurrency.one.annotations.NotRecommend;
import xyz.gabear.learn.concurrency.one.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * 线程安全的。但是可能生成了一个单例对象而没有对这个对象进行任何操作，导致资源浪费。
 * 而且如果在私有构造函数中初始化操作过多，这种饿汉模式也是不推荐的。
 */
@ThreadSafe
@NotRecommend
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2() {

    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
