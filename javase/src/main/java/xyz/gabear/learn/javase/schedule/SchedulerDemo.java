package xyz.gabear.learn.javase.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SchedulerDemo {
    private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // test1();
        // test2();
        // test3();
        // test4();
        test5();
    }

    private static void test1() throws SchedulerException {
        // 1、jobDetail包含具体需要执行的Job
        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class).withIdentity("jobDemo", "group1").build();
        // System.out.println("jobDetail's name: " + jobDetail.getKey().getName());
        // System.out.println("jobDetail's group: " + jobDetail.getKey().getGroup());
        // System.out.println("jobDetail's class: " + jobDetail.getJobClass().getName());

        // 2、trigger定义Job执行频率
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();
        // 3、创建scheduler，开始调度任务(任务、频率)
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        System.out.println("Current time is " + sf.format(new Date()));
        scheduler.scheduleJob(jobDetail, trigger);

        /*Current time is 2018-09-21 17:07:12
        Job is being executed at 2018-09-21 17:07:12
        Job is being executed at 2018-09-21 17:07:14
        Job is being executed at 2018-09-21 17:07:16
        Job is being executed at 2018-09-21 17:07:18*/
    }

    private static void test2() throws SchedulerException {
        // 1、jobDetail包含具体需要执行的Job
        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class).withIdentity("jobDemo", "group1")
                // 存储一些自定义信息
                .usingJobData("name", "gabear").usingJobData("age", 20)
                .build();

        // 2、trigger定义Job执行频率
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                // 存储一些自定义信息
                .usingJobData("name", "yan").usingJobData("high", 175.5)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();
        // 3、创建scheduler，开始调度任务(任务、频率)
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        System.out.println("Current time is " + sf.format(new Date()));
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private static void test3() throws SchedulerException {
        // 1、jobDetail包含具体需要执行的Job
        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class).withIdentity("jobDemo", "group1").build();

        // 2、trigger定义Job执行频率
        Date now = new Date();
        Date start = new Date(now.getTime() + 3000);
        Date end = new Date(now.getTime() + 6000);
        // 可以指定开始时间、结束时间、重复次数。假如指定重复100次，但是执行50次之后就到了结束时间，这时任务就会强制结束
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startAt(start)
                .endAt(end)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();
        // 3、创建scheduler，开始调度任务(任务、频率)
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        System.out.println("Current time is " + sf.format(new Date()));
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private static void test4() throws SchedulerException {
        // 1、jobDetail包含具体需要执行的Job
        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class).withIdentity("jobDemo", "group1").build();

        // 2、trigger定义Job执行频率
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                // 配置Cron表达式。这里设置每秒钟都执行
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *"))
                .build();
        // 3、创建scheduler，开始调度任务(任务、频率)
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        System.out.println("Current time is " + sf.format(new Date()));
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private static void test5() throws SchedulerException, InterruptedException {
        // 1、jobDetail包含具体需要执行的Job
        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class).withIdentity("jobDemo", "group1").build();

        // 2、trigger定义Job执行频率
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                // 配置Cron表达式。这里设置每秒钟都执行
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *"))
                .build();
        // 3、创建scheduler，开始调度任务(任务、频率)
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        System.out.println("Current time is " + sf.format(new Date()));
        scheduler.scheduleJob(jobDetail, trigger);
        Thread.sleep(2000);
        scheduler.standby(); // 调度挂起，可以通过start()重启
        Thread.sleep(3000);
        scheduler.start();
        Thread.sleep(3000);
        scheduler.shutdown(); // 调度关闭，不可重启。
    }
}
