package xyz.gabear.learn.concurrency.one.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import xyz.gabear.learn.concurrency.one.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample5 {
    // AtomicIntegerFieldUpdater类的作用就是原子性的更新所给的类中的所给成员变量。该变量要求用volatile、非static修饰。
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    @Getter
    private volatile int count = 100;

    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 100, 200)) {
            log.info("update success 1, {}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 200)) {
            log.info("update success 2, {}", example5.getCount());
        } else {
            log.info("update failed, {}", example5.getCount());
        }
    }
}
