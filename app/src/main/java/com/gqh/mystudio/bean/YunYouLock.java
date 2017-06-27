package com.gqh.mystudio.bean;

/**
 * Created by Administrator on 2017/6/12.
 */

public class YunYouLock {

    /**
     * desc : LOCK_NOT_EXIST
     * success : false
     */

    private String desc;
    private boolean success;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "YunYouLock{" +
                "desc='" + desc + '\'' +
                ", success=" + success +
                '}';
    }
}
