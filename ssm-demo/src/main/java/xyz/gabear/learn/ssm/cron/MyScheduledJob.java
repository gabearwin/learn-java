package xyz.gabear.learn.ssm.cron;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyScheduledJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(MyScheduledJob.class);

    private DataBean dataBean;

    public void setDataBean(DataBean dataBean) {
        this.dataBean = dataBean;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("MyScheduledJob Executes!" + sf.format(new Date()));
        dataBean.printMessage();
    }
}
