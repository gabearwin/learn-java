package xyz.gabear.learn.concurrency.one.example.count;

import lombok.extern.slf4j.Slf4j;
import xyz.gabear.learn.concurrency.one.annotations.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class CountExample4 {

    // 总请求数
    private static int clientTotal = 5000;
    // 同一时刻允许最大并发数
    private static int threadTotal = 200;

    // 即使使用了volatile修饰，该例子仍然是【线程不安全】的。
    // volatile不具有原子性
    private static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count: {}", count);
    }

    private static void add() {
        // 问题的根源
        count++;
        // 1、读取count
        // 2、+1
        // 3、写回count
    }
}
