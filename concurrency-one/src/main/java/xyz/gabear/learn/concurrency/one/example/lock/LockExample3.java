package xyz.gabear.learn.concurrency.one.example.lock;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 这里是举了一个例子，将私有的map成员进行封装，只对外提供部分操作都是线程安全的。
 * 注意获取写锁writeLock.lock()时，只有当前所有的读和写都没有获得锁的时候才能获取写锁。
 * 当线程有很多读操作而写操作很少的时候，写操作可能发生饥饿，因为它一直在等待当前的读操作释放读锁。这是悲观锁。
 */
public class LockExample3 {
    private final Map<String, Data> map = new TreeMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data data) {
        writeLock.lock();
        try {
            return map.put(key, data);
        } finally {
            writeLock.unlock();
        }
    }

    class Data {
    }
}
