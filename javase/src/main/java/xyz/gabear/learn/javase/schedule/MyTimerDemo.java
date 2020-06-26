package xyz.gabear.learn.javase.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerDemo {
    private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        // test1();
        // test2();
        // test3();
        // test4();
        test5();
    }

    // 了解Timer和TimerTask的基本使用
    private static void test1() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task is being executed at " + sf.format(scheduledExecutionTime()));
            }
        };
        Calendar calendar = Calendar.getInstance();
        System.out.println("Current time is " + sf.format(calendar.getTime()));
        // 延迟1秒开始执行，之后每次间隔两秒
        timer.schedule(timerTask, 1000L, 2000L);
        // 注意以下方法的使用，请直接查看官方文档
        // timerTask.scheduledExecutionTime();
        // timerTask.cancel();
        // timer.cancel();
        // timer.purge();

        /*Current time is 2018-09-21 15:09:08
        Task is being executed at 2018-09-21 15:09:09
        Task is being executed at 2018-09-21 15:09:11
        Task is being executed at 2018-09-21 15:09:13
        Task is being executed at 2018-09-21 15:09:15*/
    }

    // schedule：首次计划执行时间在当前时间之前
    private static void test2() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task is being executed at " + sf.format(scheduledExecutionTime()));
            }
        };
        Calendar calendar = Calendar.getInstance();
        System.out.println("Current time is " + sf.format(calendar.getTime()));
        calendar.add(Calendar.SECOND, -6);
        // 计划首次执行时间是6秒之前，然后间隔两秒执行
        timer.schedule(timerTask, calendar.getTime(), 2000L);

        // 实际上是从当前时间开始立即执行，然后间隔两秒执行
        /*Current time is 2018-09-21 15:24:04
        Task is being executed at 2018-09-21 15:24:04
        Task is being executed at 2018-09-21 15:24:06
        Task is being executed at 2018-09-21 15:24:08*/
    }

    // schedule：任务执行时间超过设定的时间间隔
    private static void test3() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task is being executed at " + sf.format(scheduledExecutionTime()));
            }
        };
        Calendar calendar = Calendar.getInstance();
        System.out.println("Current time is " + sf.format(calendar.getTime()));
        // 任务执行需要3秒，但是两次任务间隔只设为2秒
        timer.schedule(timerTask, calendar.getTime(), 2000L);

        // 实际上是上次任务执行完之后才立即接着执行下次任务
        /*Current time is 2018-09-21 15:40:31
        Task is being executed at 2018-09-21 15:40:31
        Task is being executed at 2018-09-21 15:40:34
        Task is being executed at 2018-09-21 15:40:37*/
    }

    // scheduleAtFixedRate：首次计划执行时间在当前时间之前
    private static void test4() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task is being executed at " + sf.format(scheduledExecutionTime()));
            }
        };
        Calendar calendar = Calendar.getInstance();
        System.out.println("Current time is " + sf.format(calendar.getTime()));
        calendar.add(Calendar.SECOND, -6);
        // 计划首次执行时间是6秒之前，然后间隔两秒执行
        timer.scheduleAtFixedRate(timerTask, calendar.getTime(), 2000L);

        // 实际上是立即执行三次赶上进度，然后从当前时间开始执行、间隔两秒执行下一次
        /*Current time is 2018-09-21 15:26:34
        Task is being executed at 2018-09-21 15:26:28
        Task is being executed at 2018-09-21 15:26:30
        Task is being executed at 2018-09-21 15:26:32
        Task is being executed at 2018-09-21 15:26:34
        Task is being executed at 2018-09-21 15:26:36
        Task is being executed at 2018-09-21 15:26:38
        Task is being executed at 2018-09-21 15:26:40*/
    }

    // scheduleAtFixedRate：任务执行时间超过设定的时间间隔
    private static void test5() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task is being executed at " + sf.format(scheduledExecutionTime()));
            }
        };
        Calendar calendar = Calendar.getInstance();
        System.out.println("Current time is " + sf.format(calendar.getTime()));
        // 任务执行需要3秒，但是两次任务间隔只设为2秒
        timer.scheduleAtFixedRate(timerTask, calendar.getTime(), 2000L);

        // 不管任务执行需要多少时间，必须按照计划的时间执行下次任务
        /*Current time is 2018-09-21 15:52:12
        Task is being executed at 2018-09-21 15:52:12
        Task is being executed at 2018-09-21 15:52:14
        Task is being executed at 2018-09-21 15:52:16*/
    }

    /*Timer的缺陷：
    1、不支持并发执行任务
    2、一个任务抛出异常，Timer下所有任务都将停止*/
}
