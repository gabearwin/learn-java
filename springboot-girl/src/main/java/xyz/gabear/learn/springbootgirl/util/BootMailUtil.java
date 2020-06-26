package xyz.gabear.learn.springbootgirl.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class BootMailUtil {
    // 使用账户名当做发件人
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送简单邮件
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param content 邮件内容(普通文本)
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
    }

    /**
     * 发送一封HTML邮件
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param content 邮件内容(拼凑出HTML格式内容)
     * @throws MessagingException
     */
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        this.sendAttachmentMail(to, subject, content, null);
    }

    /**
     * 发送HTML带附件的邮件
     *
     * @param to        邮件收件人
     * @param subject   邮件主题
     * @param content   邮件内容(HTML)
     * @param filePaths 附件文件名组成的List
     * @throws MessagingException
     */
    public void sendAttachmentMail(String to, String subject, String content, List<String> filePaths) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        // 添加附件
        if (null != filePaths && filePaths.size() != 0) {
            for (String filePath : filePaths) {
                FileSystemResource file = new FileSystemResource(new File(filePath));
                // 第一个参数表示附件在邮件显示时的名字，第二个参数是附件文件。这里我们直接使用文件本身的名字。
                helper.addAttachment(file.getFilename(), file);
            }
        }
        mailSender.send(mimeMessage);
    }

    /**
     * 发送HTML带资源图片的邮件
     *
     * @param to      邮件收件人
     * @param subject 邮件主题
     * @param content 邮件内容(HTML)
     * @param src     资源ID和资源路径组成的Map，对应在HTML中ID和src
     * @throws MessagingException
     */
    public void sendInlineMail(String to, String subject, String content, @NotNull Map<String, String> src) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        for (Map.Entry<String, String> entry : src.entrySet()) {
            // 添加一个资源
            FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
            helper.addInline(entry.getKey(), file);
        }
        mailSender.send(mimeMessage);
    }

    /**
     * 发送注册激活模板邮件
     *
     * @param to    邮件收件人
     * @param value 激活码
     * @throws MessagingException
     */
    public void sendRegisterTemplateMail(String to, String value) throws MessagingException {
        // 设置并解析thymeleaf模板
        Context context = new Context();
        context.setVariable("id", value);
        String emailContent = templateEngine.process("mailTemplate", context);
        this.sendHtmlMail(to, "注册激活邮件", emailContent);
    }

}
