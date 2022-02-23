package com.maxpicca.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Maxpicca
 * @Date 2021-02-26 15:44
 */
@Data
public class TodoAddDto implements Serializable {
    // TODO Long => int
    private int id;
    // TODO Long => int
    private int userid;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private String priority;
    private String remark;
    private Boolean status=false;
//    private String attachments;
}
