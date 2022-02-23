package com.maxpicca.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Maxpicca
 * @Date 2021-02-26 16:01
 */
@Data
public class RegisterDto implements Serializable {
    private String username;
    private String email;
    private String password;
}
