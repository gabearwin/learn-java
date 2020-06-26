package xyz.gabear.learn.ssm.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("jobBean")
public class JobBean {
    private static final Logger logger = LoggerFactory.getLogger(JobBean.class);

    public void printMessage() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("JobBean Executes!" + sf.format(new Date()));
    }
}
