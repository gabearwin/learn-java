package xyz.gabear.learn.concurrency.one.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {
    private static final int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            exec.execute(() -> {
                try {
                    test(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await(10, TimeUnit.MILLISECONDS); // 只等待10ms
        log.info("finish"); // 由于线程池中的线程都会先休眠200ms再执行，所以主线程的finish语句会最先执行。
        exec.shutdown();
    }

    private static void test(int i) throws InterruptedException {
        Thread.sleep(200);
        log.info("{}", i);
    }
}
