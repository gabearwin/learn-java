package xyz.gabear.learn.ssm.util;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static final Logger log = LoggerFactory.getLogger(DateHelper.class);
    private static final String FORMAT_DATE = "yyyy-MM-dd";
    private static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final long MILLI_SECONDS_PER_DAY = 24 * 60 * 60 * 1000;

    public static String getFormatDate(Date date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE);
            return format.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getFormatTime(Date date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_TIME);
            return format.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_TIME);
        return sdf.format(new Date());
    }

    public static int getIntDate(Date date) {
        return NumberUtils.toInt(getFormatDate(date));
    }

}
