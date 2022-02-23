package com.maxpicca.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Maxpicca
 * @Date 2021-02-24 14:49
 */
@Data
public class LoginDto implements Serializable {
//    @NotBlank(message = "用户名不能为空")
    private String username;

//    @NotBlank(message = "密码不能为空")
    private String password;
}
