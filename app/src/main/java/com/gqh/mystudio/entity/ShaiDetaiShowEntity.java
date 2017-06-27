package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/9
 * desc:
 */
public class ShaiDetaiShowEntity  implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String ShowCont;

    private int ShowID;

    private String CreateTime;

    private String ShowPic;

    public void setShowCont(String ShowCont){
        this.ShowCont = ShowCont;
    }
    public String getShowCont(){
        return this.ShowCont;
    }
    public void setShowID(int ShowID){
        this.ShowID = ShowID;
    }
    public int getShowID(){
        return this.ShowID;
    }
    public void setCreateTime(String CreateTime){
        this.CreateTime = CreateTime;
    }
    public String getCreateTime(){
        return this.CreateTime;
    }
    public void setShowPic(String ShowPic){
        this.ShowPic = ShowPic;
    }
    public String getShowPic(){
        return this.ShowPic;
    }
}
