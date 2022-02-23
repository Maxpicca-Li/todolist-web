package com.maxpicca.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Maxpicca
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false) // 自动的给model bean实现equals方法和hashcode方法,callsuper 不调用父类属性
@Accessors(chain = true) // chain为 true时，setter方法返回当前对象
@TableName("todo")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    // TODO Long => int
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    // TODO Long => int
    private int userid;

    private String title;

    private String remark;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private String priority;

    private String attachments;

    private boolean status;


}
