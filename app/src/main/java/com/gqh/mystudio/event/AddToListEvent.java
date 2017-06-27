package com.gqh.mystudio.event;

import com.gqh.mystudio.entity.ShangpinListEntity;

/**
 * Created by 红哥哥 on 2016/8/15.
 * 添加数据到购物车
 */
public class AddToListEvent {

    private ShangpinListEntity data;
    public AddToListEvent(ShangpinListEntity data) {
        this.data=data;
    }

    public ShangpinListEntity getData() {
        return data;
    }

    public void setData(ShangpinListEntity data) {
        this.data = data;
    }
}
