package com.maxpicca.controller;

import com.maxpicca.entity.Todo;
import com.maxpicca.entity.User;
import com.maxpicca.service.TodoService;
import com.maxpicca.service.UserService;
import com.maxpicca.service.impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * @author Maxpicca
 * @Date 2021-03-05 0:55
 */
@RestController
public class MailController {
    @Autowired
    MailServiceImpl mailService;

    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;

//    @Autowired
//    TemplateEngine templateEngine;  // 动态加载HTML内容的模板引擎

//    @Scheduled(cron="0 */1 * * * ?")
    public String sendTodoMark(){
        Todo todo = todoService.getById(23);
        User user = userService.getById(todo.getUserid());
        System.out.println("====正在发送任务提醒邮件====");
        try {
            mailService.sendMail(user.getEmail(),"Todo提醒："+todo.getTitle(),todo.getRemark(),null);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
