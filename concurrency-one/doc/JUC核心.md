# AQS(AbstractQueuedSynchronizer)核心组件
### CountDownLatch
1. 指定线程数(计数器)
2. 在线程中调用`countDown()`方法
3. 调用`await()`方法等待所有线程执行完(计数器减为零)再往下执行

**应用**：一个或多个线程等待其它线程执行完然后自己再执行。

### Semaphore
1. 指定许可数
2. 获取许可`acquire()`
3. 线程执行完自己的代码后释放许可`release()`

**应用**：
- 控制线程并发数量
- 控制多线程并发情况下对有限资源的访问(比如数据库只支持有限N个连接，那么就需要控制并发下最多只能同时建立N个连接)

### CyclicBarrier
1. 指定多少个线程到达屏障后才能继续同时往下执行
2. 调用`await()`方法使当前线程在屏障前等待

**应用**：多个线程内相互等待，当给定线程数到达屏障后同时往下执行

# 锁Lock
### ReentrantLock和Synchronized
1. 可重入性。线程进入锁时计数器+1，退出锁时-1。在这点上二者差别不大，都是可重入的。
2. 锁的实现。ReentrantLock基于JDK实现的，我们可以直接查看到其源码；Synchronized是JVM实现的，是虚拟机底层指令控制的，不好看到源码。
3. 性能。Synchronized引入偏向锁和自旋锁之后性能已经很好了，二者已经差不多了。这是借鉴了Lock中的CAS，在用户态就把加锁问题解决，避免
进入到内核态阻塞状态。
4. 功能区别：
    1. 加锁解锁。Synchronized直接由编译器加锁解锁，不像Lock那样需要手动显示释放锁。
    2. 锁的粒度。这方面Lock是优于Synchronized的。
5. ReentrantLock独有的功能：
    1. 可以指定是公平锁(先等待的线程先获得锁)还是非公平锁。而Synchronized只能是非公平锁。
    2. 提供一个condition类，可以分组唤醒需要唤醒的线程。而Synchronized只能随机唤醒一个线程或者唤醒全部线程。
    3. 提供能够中断等待锁的线程的机制，`lock.lockInterruptibly();`。 
    
# BlockingQueue
| | 抛出异常 | 特殊值 | 阻塞 | 超时|
| :---: | :---: | :---: | :---: | :---: |
|插入|add(e)|offer(e)|put(e)|offer(e, time, unit)|
|移除|remove()|poll()|take()|poll(time, unit)|
|检查|element()|peek()|||

