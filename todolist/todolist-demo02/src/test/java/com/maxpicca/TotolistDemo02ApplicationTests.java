package com.maxpicca;

import com.maxpicca.entity.Todo;
import com.maxpicca.entity.User;
import com.maxpicca.service.TodoService;
import com.maxpicca.service.UserService;
import com.maxpicca.service.impl.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;

@SpringBootTest
class TotolistDemo02ApplicationTests {
    @Autowired
    MailServiceImpl mailService;


    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;



    /*@Test
    void contextLoads() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Hello Maxpicca!");
        msg.setText("加油呀~");
        msg.setTo("1978451498@qq.com");
        msg.setFrom("1978451498@qq.com");
        mailSender.send(msg);
    }*/

/*    @Test
    @Scheduled(cron="0 19 1 * * ?")
    void sendTodoMark(){
        System.out.println("====正在发送任务提醒邮件====");
        Todo todo = todoService.getById(23);
        User user = userService.getById(todo.getUserid());
        try {
            mailService.sendMail(user.getEmail(),"Todo提醒："+todo.getTitle(),todo.getRemark(),null);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }*/

}
