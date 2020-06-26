package xyz.gabear.learn.ssm.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class MailUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(MailUtilsTest.class);
    @Autowired
    private MailUtils mailUtils;

    @Test
    public void sendSimpleMail() {
    }

    @Test
    public void sendMail() {
        try {
            String iqiyi = "xionggaoxiong_sx@qiyi.com";
            // 1、简单邮件
            mailUtils.sendSimpleMail(iqiyi, "感谢信", "亲爱的gabear，感谢使用Java发送邮件");
            // 2、模板邮件  模板邮件在controller中可以正常发送。但是这里测试时会抛出IOException
            Map<String, Object> map = new HashMap<>();
            map.put("name", "gabear");
            mailUtils.sendTempMail(new String[]{iqiyi}, "欢迎信", "mailTemp/welcome.ftl", map);
            // 3、附件邮件
            mailUtils.sendMail(new String[]{iqiyi}, "学习材料",
                    "有附件的邮件，你看得到吗", new String[]{"C:\\Users\\xionggaoxiong_sx\\Desktop\\Java_manual.pdf", "C:\\Users\\xionggaoxiong_sx\\Desktop\\bug.txt"});
            logger.warn("success");
        } catch (Exception e) {
            logger.warn("false", e);
        }
    }

    @Test
    public void sendMail1() {
    }

    @Test
    public void sendMail2() {
    }

    @Test
    public void sendTempMail() {
    }

    @Test
    public void sendTempMail1() {
    }
}