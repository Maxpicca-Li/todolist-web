package com.maxpicca.service.impl;

import com.maxpicca.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author Maxpicca
 * @Date 2021-03-05 0:42
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送邮件
     * @param to
     * @param subject
     * @param text
     * @param filePath
     * @throws MessagingException
     */
    @Override
    public void sendMail(String to, String subject, String text, String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        //true表示需要创建一个multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text); // true 设置html格式
        // 判断是否带有附件
        if (filePath != null) {
            FileSystemResource file = new FileSystemResource(new File(filePath));
            // String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);
        }
        mailSender.send(message);
        System.out.println("html邮件发送成功");
    }
}
