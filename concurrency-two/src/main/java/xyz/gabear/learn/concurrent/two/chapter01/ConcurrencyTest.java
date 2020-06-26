package xyz.gabear.learn.concurrent.two.chapter01;

/**
 * 串行和并发时间对比测试
 * 循环次数为1万的时候，串行更快；循环次数为10亿的时候，并发比串行快一倍
 */
public class ConcurrencyTest {

    // 总循环次数
    private static final long count = 10000L;

    public static void main(String[] args) throws InterruptedException {
        // 并发执行
        concurrency();
        // 串行
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
                System.out.println(a);
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }

}
