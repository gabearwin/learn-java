package xyz.gabear.learn.designpattern.singleton;

public class SingletonExample1 {
    private SingletonExample1() {
    }

    private volatile static SingletonExample1 instance = null;

    public static SingletonExample1 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample1.class) {
                if (instance == null) {
                    return new SingletonExample1();
                }
            }
        }
        return instance;
    }
}
