package xyz.gabear.learn.javase.exercise;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程学习
 */
public class ThreadDemo {

    public void startThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("new thread");
            }
        });
        thread.start();
    }

    //设置超时时间超时异常处理
    public void timeOut() {
        final ExecutorService executor = Executors.newSingleThreadExecutor();

        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //开始执行耗时操作
                Thread.sleep(1000 * 50);
                return "指定时间内处理完毕";
            }
        });

        executor.submit(task);

        try {
            String obj = task.get(10, TimeUnit.SECONDS); //任务处理超时时间设为 10 秒
            System.out.println("任务返回:" + obj);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("指定时间内处理失败");
        } finally {
            executor.shutdown();
        }
    }

    // 三个线程输出 hello_A_B_C
    public void alternatePrint() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.print("hello");
        Thread threadA = new Thread(() -> {
            while (atomicInteger.get() < 3) {
                while (atomicInteger.get() % 3 == 0 && atomicInteger.get() < 3) {
                    System.out.print("_A");
                    atomicInteger.incrementAndGet();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (atomicInteger.get() < 3) {
                while (atomicInteger.get() % 3 == 1) {
                    System.out.print("_B");
                    atomicInteger.incrementAndGet();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            while (atomicInteger.get() < 3) {
                while (atomicInteger.get() % 3 == 2) {
                    System.out.println("_C");
                    atomicInteger.incrementAndGet();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }

    public static void main(String[] args) {
        new ThreadDemo().startThread();
        new ThreadDemo().timeOut();
        new ThreadDemo().alternatePrint();
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println();
    }
}

class MyThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println();
    }
}

class MyThread3 implements Callable<ThreadDemo> {

    @Override
    public ThreadDemo call() throws Exception {
        return null;
    }
}