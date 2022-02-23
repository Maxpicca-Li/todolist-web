package com.maxpicca.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maxpicca.common.Result;
import com.maxpicca.dto.RegisterDto;
import com.maxpicca.entity.User;
import com.maxpicca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Maxpicca
 * @Date 2021-02-26 15:42
 */
@RestController
public class RegisterController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto registerDto){
        // TODO 注册逻辑
        System.out.println("=========================后端之注册========================");
        User user1 = userService.getOne(new QueryWrapper<User>().eq("username", registerDto.getUsername()));
        if (user1!=null){
            return Result.error("用户名已使用");
        }
        // User newUser = new User();
        // BeanUtil.copyProperties(registerDto, newUser, "username", "email", "password");
        User user= dto2user(registerDto);
        userService.saveOrUpdate(user);
        System.out.println("=========================注册成功========================");
        return Result.ok(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("email", user.getEmail())
                .map()
        );
    }

    private User dto2user(RegisterDto registerDto){
        User newUser = new User();
        newUser.setUsername(registerDto.getUsername());
        newUser.setEmail(registerDto.getEmail());
        newUser.setPassword(registerDto.getPassword());
        newUser.setCreateTime(LocalDateTime.now());
        newUser.setUpdateTime(LocalDateTime.now());
        newUser.setStatus(0);
        return newUser;
    }
}
