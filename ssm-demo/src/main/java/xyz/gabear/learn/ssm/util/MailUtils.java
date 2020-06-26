package xyz.gabear.learn.ssm.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 自己封装的email工具类
 * Created By xionggaoxiong_sx on 2018/7/20.
 */
@Component
public class MailUtils {
    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);

    // MailSender用来发送简单邮件，复杂内容邮件如mime等需要使用它的拓展接口JavaMailSender
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 最简单的实现邮件发送功能
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String to, String subject, String content) {
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            logger.error("MessagingException", e);
        }
    }

    /**
     * 发送普通文本邮件
     *
     * @param receivers 收件人，字符串数组
     * @param subject   邮件主题
     * @param content   邮件内容
     */
    public void sendMail(String[] receivers, String subject, String content) {
        sendMail(receivers, null, null, subject, content, null);
    }

    /**
     * 发送文本邮件，带附件
     *
     * @param receivers  收件人，字符串数组
     * @param subject    邮件主题
     * @param content    邮件内容
     * @param attachment 邮件附件
     */
    public void sendMail(String[] receivers, String subject, String content, String[] attachment) {
        sendMail(receivers, null, null, subject, content, attachment);
    }

    /**
     * 发送文本邮件，抄送人和密送人，带附件
     *
     * @param receivers  收件人，字符串数组
     * @param ccs        抄送人，字符串数组
     * @param bcc        密送人，字符串数组
     * @param subject    邮件主题
     * @param content    邮件内容
     * @param attachment 邮件附件
     */
    public void sendMail(String[] receivers, String[] ccs, String[] bcc, String subject, String content, String[] attachment) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "UTF-8");
            msgHelper.setTo(receivers);
            if (ccs != null && ccs.length > 0) {
                msgHelper.setCc(ccs);
            }
            if (bcc != null && bcc.length > 0) {
                msgHelper.setBcc(bcc);
            }
            for (String filePath : attachment) {
                FileSystemResource file = new FileSystemResource(new File(filePath));
                String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                msgHelper.addAttachment(fileName, file);
            }
            msgHelper.setFrom(simpleMailMessage.getFrom());
            msgHelper.setSubject(simpleMailMessage.getSubject() + subject);
            msgHelper.setText(content, true);
            // 发送邮件
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            logger.error("MessagingException", e);
        }

    }

    /**
     * 发送模板邮件
     *
     * @param receivers     收件人
     * @param subject       邮件主题
     * @param templateName  模板名字
     * @param templateValue 模板数据
     */
    public void sendTempMail(String[] receivers, String subject,
                             String templateName, Map<String, Object> templateValue) {
        sendTempMail(receivers, null, null, subject, templateName, templateValue);
    }

    /**
     * 发送模板邮件
     *
     * @param receivers     收件人
     * @param ccs           抄送人
     * @param bcc           密送人
     * @param subject       邮件主题
     * @param templateName  模板名字
     * @param templateValue 模板数据
     */
    public void sendTempMail(String[] receivers, String[] ccs, String[] bcc, String subject,
                             String templateName, Map<String, Object> templateValue) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "UTF-8");
            msgHelper.setTo(receivers);
            if (ccs != null && ccs.length > 0) {
                msgHelper.setCc(ccs);
            }
            if (null != bcc && bcc.length > 0) {
                msgHelper.setBcc(bcc);
            }
            msgHelper.setFrom(simpleMailMessage.getFrom());
            msgHelper.setSubject(simpleMailMessage.getSubject() + subject);
            // 设定替换Freemarker模板上的数据
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateValue);
            msgHelper.setText(htmlText, true);
            // 发送邮件
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            logger.error("MessagingException", e);
        } catch (TemplateException e) {
            logger.error("TemplateException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }
}
