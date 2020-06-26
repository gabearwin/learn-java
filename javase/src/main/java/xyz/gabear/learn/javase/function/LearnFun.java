package xyz.gabear.learn.javase.function;

import java.util.function.Function;


interface Say {
    static String hello(String name) {
        System.out.println("hello friend");
        return name;
    }

    void hi();
}

public class LearnFun {
    <T, R> void test(Function<T, R> function, T param) {
        System.out.println(111);
        function.apply(param);
        System.out.println(222);
    }

    public static void main(String[] args) {
        LearnFun learnFun = new LearnFun();
        learnFun.test(Say::hello, null);
    }
}
