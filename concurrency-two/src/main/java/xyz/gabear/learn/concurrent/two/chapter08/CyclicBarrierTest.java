package xyz.gabear.learn.concurrent.two.chapter08;import java.util.concurrent.CyclicBarrier;public class CyclicBarrierTest {    static CyclicBarrier c = new CyclicBarrier(2);    public static void main(String[] args) {        new Thread(new Runnable() {            @Override            public void run() {                try {                    c.await();                } catch (Exception e) {                    // ...                }                System.out.println(1);            }        }).start();        try {            c.await();        } catch (Exception e) {            // ...        }        System.out.println(2);    }}// 让一组线程到达一个屏障时被阻塞(通过await方法表明已到达)，直到最后一个线程到达屏障，屏障会开门，所有线程都开始继续执行// 上例中可能输出1 2 也可能输出2 1，因为主线程和子线程的调度是由CPU决定的。// 如果把构造方法中参数改为3，那么程序没有任何输出，因为没有第三个线程执行await方法，两个线程都被阻塞了，不会执行后面的输出语句