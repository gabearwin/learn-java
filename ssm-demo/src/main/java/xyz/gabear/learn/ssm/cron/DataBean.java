package xyz.gabear.learn.ssm.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("dataBean")
public class DataBean {
    private static final Logger logger = LoggerFactory.getLogger(DataBean.class);

    public void printMessage() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("DataBean do something..." + sf.format(new Date()));
    }
}
