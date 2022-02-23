package com.maxpicca.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Maxpicca
 * @Date 2021-03-03 23:24
 */
@Data
public class PwdDto implements Serializable {
    private int id;
    private String oldPwd;
    private String newPwd;
    private String renewPwd;
}
