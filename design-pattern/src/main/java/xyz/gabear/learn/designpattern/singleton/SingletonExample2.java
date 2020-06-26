package xyz.gabear.learn.designpattern.singleton;

public class SingletonExample2 {
    private SingletonExample2() {
    }

    public static SingletonExample2 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    public enum Singleton {
        INSTANCE;
        private SingletonExample2 singleton;

        Singleton() {
            singleton = new SingletonExample2();
        }

        public SingletonExample2 getInstance() {
            return singleton;
        }
    }
}
