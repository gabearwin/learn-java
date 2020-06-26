package xyz.gabear.learn.concurrency.one.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        // 延迟给定的时间后执行
        /*executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        }, 3, TimeUnit.SECONDS);*/

        // 以固定的速率调度执行
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        }, 1, 3, TimeUnit.SECONDS);

        // executorService.shutdown();

        // Timer定时器也可以达到间隔时间一直运行的效果
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 5 * 1000);
    }
}
