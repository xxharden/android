package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/8
 * desc:
 */
public class FileEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    private String filename;

    private String url;

    public void setFilename(String filename){
        this.filename = filename;
    }
    public String getFilename(){
        return this.filename;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
}
