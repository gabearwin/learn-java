package xyz.gabear.learn.concurrency.one.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的Condition类
 */
@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("wait signal"); // 1. 首先获得锁，进入AQS队列，输出。然后执行condition.await()进入条件等待队列。
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4. 线程被重新唤醒。继续执行。
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("get lock"); // 2. 线程2获得锁，输出
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signal();
            condition.signalAll(); // 唤醒所有等待条件的线程，让它们进入AQS队列，但他们不会立即执行。也可以调用condition.signal()单个唤醒。
            log.info("send signal ~ "); // 3
            reentrantLock.unlock(); // 上面输出完之后释放锁。此时AQS队列将会唤醒所有线程。
        }).start();
    }
}
