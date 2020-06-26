package xyz.gabear.learn.concurrency.one;

import lombok.extern.slf4j.Slf4j;
import xyz.gabear.learn.concurrency.one.annotations.NotThreadSafe;

import java.util.concurrent.*;

@Slf4j
@NotThreadSafe
public class ConcurrencyTest {
    // 总请求数
    private static int clientTotal = 5000;
    // 同一时刻允许最大并发数
    private static int threadTotal = 200;

    private static int count = 0;

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
        count++;
    }
}
