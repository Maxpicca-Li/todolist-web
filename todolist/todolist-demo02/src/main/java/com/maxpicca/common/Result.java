package com.maxpicca.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Maxpicca
 * @Date 2021-02-24 14:27
 */
@Data
public class Result implements Serializable {
    private int code; // 200正常,400异常
    private String msg;
    private Object data;

    public static Result ok(Object data) { return ok(200,"操作成功",data); }
    public static Result ok(String mess, Object data) {
        return ok(200,mess,data);
    }
    public static Result ok(int code, String mess, Object data) {
        Result m = new Result();
        m.setCode(code);
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

    public static Result error(String msg) {
        return error(400,msg,null);
    }
    public static Result error(String msg, Object data) {
        return error(-1,msg,data);
    }
    public static Result error(int code, String mess, Object data) {
        Result m = new Result();
        m.setCode(code);
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

}

