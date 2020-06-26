package xyz.gabear.learn.ssm.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanHelper {
    private static final Logger logger = LoggerFactory.getLogger(BeanHelper.class);

    public static Map bean2Map(Object bean) {
        Map map = null;
        try {
            map = BeanUtils.describe(bean);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            String err = "参数转换错误";
            logger.error(err, e);
            throw new RuntimeException(err);
        }
        return map;
    }

    public static <T> T map2Bean(Map map, T bean) {
        // 注册转换器，不让int型默认为0，改为默认null
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        try {
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            String err = "参数转换错误";
            logger.error(err, e);
            throw new RuntimeException(err);
        }
        return bean;
    }
}
