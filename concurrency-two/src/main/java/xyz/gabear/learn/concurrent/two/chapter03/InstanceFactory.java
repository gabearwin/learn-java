package xyz.gabear.learn.concurrent.two.chapter03;

public class InstanceFactory {
    private static class InstanceHolder {
        public static Instance instance = new Instance();
    }

    public static Instance getInstance() {
        return InstanceHolder.instance; //class对象初始化会加锁，这是关键
    }

    static class Instance {
    }
}
