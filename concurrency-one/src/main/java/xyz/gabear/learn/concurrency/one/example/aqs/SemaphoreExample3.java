package xyz.gabear.learn.concurrency.one.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 程序只有3个线程的输出。
 * 因为在这三个线程获得许可执行的时候(执行时间较长，因为执行的时候还休眠了一秒钟)，其他线程尝试获取许可都失败了，然后直接结束了。
 * 如果删除test方法中的休眠语句，可能有3、4、5...甚至20个输出。
 */
@Slf4j
public class SemaphoreExample3 {

    private final static int threadCount = 20;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    if (semaphore.tryAcquire()) { // 尝试获取一个许可
                        test(threadNum);
                        semaphore.release(); // 释放一个许可
                    }
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        log.info("{}", threadNum);
        // Thread.sleep(1000);
    }
}
