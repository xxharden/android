package com.gqh.mystudio.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ShuiBiao2 {
    /**
     * msg : [{"meterId":"1604040197","createTime":"2017-06-22 13:02:44","oweStatus":false,"powerOwe":false,"doorClose":false,"forceMode":0,"unConnect":true,"waterTatol":0,"waterRemain":-952795.27}]
     * code : 200
     */

    private String code;
    private List<MsgBean> msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * meterId : 1604040197
         * createTime : 2017-06-22 13:02:44
         * oweStatus : false
         * powerOwe : false
         * doorClose : false
         * forceMode : 0
         * unConnect : true
         * waterTatol : 0.0
         * waterRemain : -952795.27
         */

        private String meterId;
        private String createTime;
        private boolean oweStatus;
        private boolean powerOwe;
        private boolean doorClose;
        private int forceMode;
        private boolean unConnect;
        private double waterTatol;
        private double waterRemain;

        public String getMeterId() {
            return meterId;
        }

        public void setMeterId(String meterId) {
            this.meterId = meterId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isOweStatus() {
            return oweStatus;
        }

        public void setOweStatus(boolean oweStatus) {
            this.oweStatus = oweStatus;
        }

        public boolean isPowerOwe() {
            return powerOwe;
        }

        public void setPowerOwe(boolean powerOwe) {
            this.powerOwe = powerOwe;
        }

        public boolean isDoorClose() {
            return doorClose;
        }

        public void setDoorClose(boolean doorClose) {
            this.doorClose = doorClose;
        }

        public int getForceMode() {
            return forceMode;
        }

        public void setForceMode(int forceMode) {
            this.forceMode = forceMode;
        }

        public boolean isUnConnect() {
            return unConnect;
        }

        public void setUnConnect(boolean unConnect) {
            this.unConnect = unConnect;
        }

        public double getWaterTatol() {
            return waterTatol;
        }

        public void setWaterTatol(double waterTatol) {
            this.waterTatol = waterTatol;
        }

        public double getWaterRemain() {
            return waterRemain;
        }

        public void setWaterRemain(double waterRemain) {
            this.waterRemain = waterRemain;
        }
    }
}
