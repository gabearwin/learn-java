package xyz.gabear.learn.javase.schedule;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JobDemo implements Job {
    private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Job is being executed at " + sf.format(new Date()));

        // 获取JobDetail和Trigger的相关参数
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
        JobDataMap jobDetailMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JobDataMap triggerMap = jobExecutionContext.getTrigger().getJobDataMap();
        // 如果JobDetail和Trigger中DataMap的key相同，会默认使用Trigger中的key和value
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        // 在Job实现类中声明变量，变量名和JobDataMap的键值相同，为变量添加setter方法，这样就可以直接使用变量了
        // (Quartz框架的JobFactory实现类在初始化Job实例对象时会自动调用这些setter方法完成值注入)

        Date startTime = jobExecutionContext.getTrigger().getStartTime();
        Date endTime = jobExecutionContext.getTrigger().getEndTime();


    }
}
