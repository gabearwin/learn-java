package xyz.gabear.learn.springbootgirl.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestTask {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");

    //每过3秒执行一次任务
    // @Scheduled(fixedRate = 3000)
    // @Scheduled(cron = "4-40 * * * * ? ")
    public void reportCurrentTime() {
        System.out.println("现在时间：" + DATE_FORMAT.format(new Date()));
    }
}