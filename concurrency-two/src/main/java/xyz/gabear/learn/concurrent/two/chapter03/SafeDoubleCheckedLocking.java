package xyz.gabear.learn.concurrent.two.chapter03;

public class SafeDoubleCheckedLocking {
    private volatile static Instance instance;

    public static Instance getInstance() {
        if (instance == null) {
            synchronized (SafeDoubleCheckedLocking.class) {
                if (instance == null)
                    instance = new Instance();//instance为volatile就没问题了
            }
        }
        return instance;
    }

    static class Instance {
    }
}
