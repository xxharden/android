package com.gqh.mystudio.entity;

/**
 * @author : 红仔
 * @date : 2016/3/15
 * desc:
 */
public class LinkListEntity {
    private String Name;

    private String Url;

    private String SupDesc;

    private String ContactPhone;

    private String ShowSite;//显示位置， 1-轮播商家；2-中间小图商家；3-分类

    private String Pic;

    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return this.Name;
    }
    public void setUrl(String Url){
        this.Url = Url;
    }
    public String getUrl(){
        return this.Url;
    }
    public void setSupDesc(String SupDesc){
        this.SupDesc = SupDesc;
    }
    public String getSupDesc(){
        return this.SupDesc;
    }
    public void setContactPhone(String ContactPhone){
        this.ContactPhone = ContactPhone;
    }
    public String getContactPhone(){
        return this.ContactPhone;
    }
    public void setShowSite(String ShowSite){
        this.ShowSite = ShowSite;
    }
    public String getShowSite(){
        return this.ShowSite;
    }
    public void setPic(String Pic){
        this.Pic = Pic;
    }
    public String getPic(){
        return this.Pic;
    }
}
