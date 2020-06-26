package xyz.gabear.learn.concurrent.two.chapter05;

import xyz.gabear.learn.concurrent.two.chapter04.SleepUtils;

import java.util.concurrent.locks.Lock;

public class TwinsLockTest {

    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // ����10���߳�
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // ÿ��1�뻻��
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }
}
