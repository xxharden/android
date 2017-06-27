package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/7
 * desc:
 */
public class MessageEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private String createtime;

    private String content;

    private int msgTo;

    private int msgFrom;

    private int msgid;

    private int state;

    public void setCreatetime(String createtime){
        this.createtime = createtime;
    }
    public String getCreatetime(){
        return this.createtime;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setMsgTo(int msgTo){
        this.msgTo = msgTo;
    }
    public int getMsgTo(){
        return this.msgTo;
    }
    public void setMsgFrom(int msgFrom){
        this.msgFrom = msgFrom;
    }
    public int getMsgFrom(){
        return this.msgFrom;
    }
    public void setMsgid(int msgid){
        this.msgid = msgid;
    }
    public int getMsgid(){
        return this.msgid;
    }
    public void setState(int state){
        this.state = state;
    }
    public int getState(){
        return this.state;
    }

}
