package xyz.gabear.learn.javase.function;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

import java.time.Duration;

public class MyRetry {
    public static void main(String[] args) {
        // RetryPolicy retryPolicy = new RetryPolicy().withMaxRetries(2);
        // Failsafe.with(retryPolicy).onRetry(e-> System.out.println(e.getMessage())).run(r-> System.out.println(3/0));
        RetryPolicy retryPolicy = new RetryPolicy().withMaxAttempts(3).withDelay(Duration.ofSeconds(2));
        HelloA a = new HelloA();
        Failsafe.with(retryPolicy).onFailure(e-> System.out.println(e.toString())).get(a::say);

    }
}

class HelloA{
    public String say(){
        System.out.println(111);
        System.out.println(1/0);
        return "hello";
    }
}
