package xyz.gabear.learn.ssm.util;

import org.springframework.util.DigestUtils;

/**
 * 各种格式的编码加码工具类.
 */
public class EncodeUtils {
    private static final String DEFAULT_ENCODING = "UTF-8";
    // MD5盐值字符串，用于混淆MD5
    private static final String SLAT = "http://www.gabear.xyz";

    // 用于生成md5字符串
    public static String getMD5(String param) {
        String base = param + "/" + SLAT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

}
