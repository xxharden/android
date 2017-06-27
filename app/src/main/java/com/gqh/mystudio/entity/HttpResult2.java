package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/4/6
 * desc:
 */
public class HttpResult2 implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 错误消息
     */
    public String msg="fail";
    /**
     * 状态码 0 成功
     */
    public int errno;


    public int currPage;
    public int pageSize;

    public boolean isSuccess() {
        return errno==0;
    }
    public int co;
}