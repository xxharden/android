package com.gqh.mystudio.entity;

import java.util.List;

import cn.myapp.entity.HttpResult;

/**
 * @author : 红仔
 * @date : 2016/3/7
 * desc: 验证码
 */
public class MessageResult extends HttpResult {

    private static final long serialVersionUID = 1L;
    public List<MessageEntity> data;
}
