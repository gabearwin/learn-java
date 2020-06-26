package xyz.gabear.learn.concurrency.one.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1、latch.await();这一行是为了让所有线程池的线程执行完，然后主线程输出finish。
 * 所有线程包括主线程执行完之后程序并没有立即结束，这是因为线程池还没有关闭。默认过一段时间线程池自动关闭，程序结束。
 * 2、将latch.await();这一行删除，那么主线程的输出语句finish可能交叉在线程池的线程中输出。
 * 3、线程池使用之后，最好将其关闭。exec.shutdown();
 */
@Slf4j
public class CountDownLatchExample1 {
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
        latch.await();
        log.info("finish");
    }

    private static void test(int i) throws InterruptedException {
        Thread.sleep(200);
        log.info("{}", i);
        Thread.sleep(200);
    }
}
