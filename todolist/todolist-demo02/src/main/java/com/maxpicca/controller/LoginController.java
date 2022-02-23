package com.maxpicca.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maxpicca.common.Result;
import com.maxpicca.dto.LoginDto;
import com.maxpicca.entity.User;
import com.maxpicca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Maxpicca
 * @since 2021-02-24
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto, HttpSession session){  // HttpServerResponse response
        System.out.println("=========================已进入后端========================");
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
//        Assert.notNull(user,"用户不存在");
            if (user==null){
                return Result.error("用户不存在");
        }
//        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword())))  // md5密码保存
        if(!user.getPassword().equals(loginDto.getPassword())){
            return Result.error("账号或密码不正确！");
        }
        session.setAttribute("user", user);
//        String jwt = jwtUtils.generateToken(user.getId());
//        response.setHeader("Authorization", jwt);
//        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        // 用户可以另一个接口
        return Result.ok(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("email", user.getEmail())
                .map()
        );
    }

    @GetMapping("/user/{id}")
    public Object test01(@PathVariable Long id){
        return userService.getById(id);
    }
}
