package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/2/24
 * desc: 验证码 实体类
 */
public class CodeEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 验证码
     */
    private String code;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }

}
