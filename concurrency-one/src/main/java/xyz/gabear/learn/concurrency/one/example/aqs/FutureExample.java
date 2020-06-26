package xyz.gabear.learn.concurrency.one.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureExample {
    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("do something in callable.");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<String> future = exec.submit(new MyCallable());
        log.info("do something in main.");
        Thread.sleep(1000);
        log.info("result: {}", future.get()); // 可能另外一个线程还在执行，那就等它执行完再取出它返回的结果。
    }

}
