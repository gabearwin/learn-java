package xyz.gabear.learn.concurrency.one.example.syncContainer;

import xyz.gabear.learn.concurrency.one.annotations.NotThreadSafe;

import java.util.Vector;

/**
 * 同步容器中，由于线程执行顺序导致线程不安全
 * 在下面的例子中：可能线程1已经把元素5移除了，但是线程2刚开始执行，然后get(5)出现异常
 */
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.get(i);
                }
            });
            thread1.start();
            thread2.start();
        }
    }
}
