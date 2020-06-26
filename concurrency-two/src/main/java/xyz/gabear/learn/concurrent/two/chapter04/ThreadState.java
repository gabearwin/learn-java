package xyz.gabear.learn.concurrent.two.chapter04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadState {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
        new Thread(new Sync(), "SyncThread-1").start();
        new Thread(new Sync(), "SyncThread-2").start();
    }

    /**
     * 该线程不断地进行休眠
     */
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                //设置休眠时间是超时等待状态TIMED_WAITING (sleeping)
                SleepUtils.second(100);
            }
        }
    }

    /**
     * 该线程在Waiting.class实例上等待
     */
    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                //在此处无法获取到锁是阻塞状态BLOCKED
                synchronized (Waiting.class) {
                    try {
                        //在此处是等待状态 WAITING (on object monitor)
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 该线程在Blocked.class实例上加锁后，不会释放该锁
     */
    static class Blocked implements Runnable {
        @Override
        public void run() {
            //在此处无法获取到锁是阻塞状态BLOCKED
            synchronized (Blocked.class) {
                while (true) {
                    //设置休眠时间是超时等待状态TIMED_WAITING (sleeping)
                    SleepUtils.second(100);
                }
            }
        }
    }

    static class Sync implements Runnable {
        @Override
        public void run() {
            lock.lock();//未获取到锁是等待状态WAITING (parking)
            try {
                //设置休眠时间是超时等待状态TIMED_WAITING (sleeping)
                SleepUtils.second(100);
            } finally {
                lock.unlock();
            }
        }
    }

}
/*
1、Java将操作系统的运行和就绪两个状态合并为运行状态
2、未进入synchronized是阻塞状态BLOCKED，未进入lock是等待状态WAITING
*/
