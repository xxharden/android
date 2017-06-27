package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/17
 * desc:
 */
public class MyMessageListEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private String CreateTime;

    private String Content;

    public void setCreateTime(String CreateTime){
        this.CreateTime = CreateTime;
    }
    public String getCreateTime(){
        return this.CreateTime;
    }
    public void setContent(String Content){
        this.Content = Content;
    }
    public String getContent(){
        return this.Content;
    }
}
