package xyz.gabear.learn.concurrency.one.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncExample1 {
    // 修饰代码块，作用于执行该代码块的对象(括号里面的对象)
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    // 修饰方法，作用于调用该方法的对象。
    // 父类中有synchronized test2方法，子类继承该类之后，子类中的test2方法不是同步的。因为synchronized不属于方法申明的一部分。
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    // 方法中的代码全都被synchronized修饰和直接用synchronized修饰方法效果是一样的
    public static void main(String[] args) {
        SyncExample1 example1 = new SyncExample1();
        SyncExample1 example2 = new SyncExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> example1.test2(1));
        executorService.execute(() -> example2.test2(2));
        // 线程1和线程2【交叉】执行，每个线程中都是有序的输出1-9
    }
}
