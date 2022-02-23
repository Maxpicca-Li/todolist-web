package com.maxpicca.service;

import javax.mail.MessagingException;

/**
 * @author Maxpicca
 * @Date 2021-03-05 0:42
 */
public interface MailService {
    void sendMail(String to, String subject, String content, String filePath) throws Exception;
}
