package xyz.gabear.learn.concurrency.one.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncExample2 {
    // 修饰类
    public static void test1(int j) {
        synchronized (SyncExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    // 修饰一个静态方法，作用于该类的所有对象
    public synchronized static void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    // 方法中全部代码直接被synchronized修饰当前类包括，和直接用synchronized修饰该静态方法效果是一样的
    public static void main(String[] args) {
        SyncExample2 example1 = new SyncExample2();
        SyncExample2 example2 = new SyncExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> example1.test1(1));
        executorService.execute(() -> example2.test1(2));
        // 线程1和线程2【顺序】执行，每个线程中都是有序的输出1-9
    }
}
