package com.maxpicca.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maxpicca.common.Result;
import com.maxpicca.dto.CheckEmailDto;
import com.maxpicca.dto.NameDto;
import com.maxpicca.dto.PwdDto;
import com.maxpicca.entity.User;
import com.maxpicca.service.MailService;
import com.maxpicca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author Maxpicca
 * @Date 2021-03-03 23:21
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @PostMapping("/pwdEdit")
    public Result pwdEdit(@RequestBody PwdDto pwdDto) {
        System.out.println("修改密码中......");
        User user = userService.getOne(new QueryWrapper<User>().eq("id", pwdDto.getId()));
        if (user==null){
            return Result.error("用户不存在");
        }
        // if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword())))  // md5密码保存
        if(!user.getPassword().equals(pwdDto.getOldPwd())){
            return Result.error("旧密码输入不正确！");
        }
        user.setPassword(pwdDto.getNewPwd());
        userService.saveOrUpdate(user);
        return Result.ok("密码修改成功！");
       /* return Result.ok(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("email", user.getEmail())
                .map()
        );*/
    }

    @PostMapping("/nameEdit")
    public Result nameEdit(@RequestBody NameDto nameDto) {
        System.out.println("修改昵称中......");
        User user = userService.getOne(new QueryWrapper<User>().eq("id", nameDto.getId()));
        if (user==null){
            return Result.error("用户不存在");
        }
        user.setUsername(nameDto.getNewName());
        userService.saveOrUpdate(user);
        return Result.ok("昵称修改成功！");
       /* return Result.ok(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("email", user.getEmail())
                .map()
        );*/
    }

    @RequestMapping("/toCheck")
    public Result checkEmail(@RequestBody CheckEmailDto ced){
        // 0.预先判断
        String email = ced.getEmail();
        if(email==null){
            return Result.error("请输入邮箱");
        }
        System.out.println("用户"+email+"正在登录===>");
        // 1.查询是否重复
        User user = userService.getOne(new QueryWrapper<User>().eq("email", email));
        if(user!=null){
            return Result.error("账户已存在，请登录");
        }
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000); // 随机生成六位验证码
        String message = "您的注册验证码为："+checkCode;
        try {
            mailService.sendMail(email, "TodoList注册验证码", message,null);
            return Result.ok("验证码发送成功",checkCode);
        }catch (Exception e){
            return Result.error("发送邮件失误");
        }
    }
}

