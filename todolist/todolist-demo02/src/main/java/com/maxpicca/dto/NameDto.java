package com.maxpicca.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Maxpicca
 * @Date 2021-03-04 0:07
 */
@Data
public class NameDto implements Serializable {
    private int id;
    private String newName;
}