package com.gqh.mystudio.bean;

/**
 * author:wungko
 * time:16/6/20
 * display:网络请求返回基类
 */
public class BaseEntity {
    public int error_code;
    public String error_msg;

    @Override
    public String toString() {
        return "{" +
                "error_code=" + error_code +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }
}