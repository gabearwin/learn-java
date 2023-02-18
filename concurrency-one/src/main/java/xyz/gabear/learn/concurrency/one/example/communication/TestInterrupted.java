package xyz.gabear.learn.concurrency.one.example.communication;

import lombok.extern.slf4j.Slf4j;

/**
 * @date 2020/11/16
 */
@Slf4j
public class TestInterrupted {
    public static void main1(String[] args) throws InterruptedException {
        Thread childThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                log.info(Thread.currentThread() + " hello");
            }
        });
        childThread.start();
        Thread.sleep(1000L);
        log.info("mainThread interrupted childThread");
        childThread.interrupt();

        childThread.join();
        log.info("mainThread is over");
    }

    public static void main(String[] args) {
        // ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("this is message");
        Thread childThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(threadLocal.get());
        });
        childThread.start();
        log.info(threadLocal.get());
    }
}
