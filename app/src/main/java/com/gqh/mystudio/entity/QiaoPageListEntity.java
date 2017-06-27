package com.gqh.mystudio.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author : 红仔
 * @date : 2016/3/10
 * desc:
 */
public class QiaoPageListEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private  List<QiaoMoreEntity> data;

    public List<QiaoMoreEntity> getData() {
        return data;
    }

    public void setData(List<QiaoMoreEntity> data) {
        this.data = data;
    }
}
