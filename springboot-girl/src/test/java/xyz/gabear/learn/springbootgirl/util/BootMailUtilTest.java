package xyz.gabear.learn.springbootgirl.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootMailUtilTest {
    @Autowired
    private BootMailUtil bootMailUtil;

    @Test
    public void sendSimpleMail() {
        bootMailUtil.sendSimpleMail("820354391@qq.com", "国庆节快乐", "国庆节快乐！");
        log.warn("发送成功了，请查收邮件！");
    }

    @Test
    public void sendHtmlMail() throws MessagingException {
        String content = "<html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h3 style=\"color: crimson\">国庆节快乐！</h3>\n" +
                "</body>\n" +
                "</html>";
        bootMailUtil.sendHtmlMail("820354391@qq.com", "这是一封HTML邮件", content);
    }

    @Test
    public void sendAttachmentMail() throws MessagingException {
        List<String> filePaths = Arrays.asList("E:/GitHub/1.jpg", "E:/GitHub/2.jpg");
        bootMailUtil.sendAttachmentMail("820354391@qq.com", "这是一封带附件的邮件",
                "国庆节快乐", filePaths);
    }

    @Test
    public void sendInlineMail() throws MessagingException {
        HashMap<String, String> map = new HashMap<>();
        map.put("101", "E:/GitHub/1.jpg");
        map.put("102", "E:/GitHub/2.jpg");
        String content = "<html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h3 style=\"color: crimson\">国庆节快乐！</h3>\n" +
                "<img src=\"cid:" + "101" + "\" id=\"\">\n" +
                "<img src=\"cid:" + "102" + "\" id=\"\">\n" +
                "</body>\n" +
                "</html>";
        bootMailUtil.sendInlineMail("820354391@qq.com", "这是一封带图片资源的邮件", content, map);
    }

    @Test
    public void sendRegisterTemplateMail() throws MessagingException {
        bootMailUtil.sendRegisterTemplateMail("820354391@qq.com", UUID.randomUUID().toString());
    }
}