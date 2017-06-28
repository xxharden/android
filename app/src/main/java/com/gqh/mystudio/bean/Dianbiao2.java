package com.gqh.mystudio.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class Dianbiao2 {

    /**
     * msg : [{"meterId":"0202010202","createTime":"2017-06-22 13:17:46","oweMoney":false,"alarmA":false,"buyTimes":" ","controlMode":false,"ia":0,"ua":0,"powerW":0,"money":0,"openOrClose":false,"overPower":false,"powerTatol":0,"unConnect":true,"userStatus":false}]
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
         * meterId : 0202010202
         * createTime : 2017-06-22 13:17:46
         * oweMoney : false
         * alarmA : false
         * buyTimes :
         * controlMode : false
         * ia : 0.0
         * ua : 0.0
         * powerW : 0
         * money : 0.0
         * openOrClose : false
         * overPower : false
         * powerTatol : 0.0
         * unConnect : true
         * userStatus : false
         */

        private String meterId;
        private String createTime;
        private boolean oweMoney;
        private boolean alarmA;
        private String buyTimes;
        private boolean controlMode;
        private double ia;
        private double ua;
        private int powerW;
        private double money;
        private boolean openOrClose;
        private boolean overPower;
        private double powerTatol;
        private boolean unConnect;
        private boolean userStatus;

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

        public boolean isOweMoney() {
            return oweMoney;
        }

        public void setOweMoney(boolean oweMoney) {
            this.oweMoney = oweMoney;
        }

        public boolean isAlarmA() {
            return alarmA;
        }

        public void setAlarmA(boolean alarmA) {
            this.alarmA = alarmA;
        }

        public String getBuyTimes() {
            return buyTimes;
        }

        public void setBuyTimes(String buyTimes) {
            this.buyTimes = buyTimes;
        }

        public boolean isControlMode() {
            return controlMode;
        }

        public void setControlMode(boolean controlMode) {
            this.controlMode = controlMode;
        }

        public double getIa() {
            return ia;
        }

        public void setIa(double ia) {
            this.ia = ia;
        }

        public double getUa() {
            return ua;
        }

        public void setUa(double ua) {
            this.ua = ua;
        }

        public int getPowerW() {
            return powerW;
        }

        public void setPowerW(int powerW) {
            this.powerW = powerW;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public boolean isOpenOrClose() {
            return openOrClose;
        }

        public void setOpenOrClose(boolean openOrClose) {
            this.openOrClose = openOrClose;
        }

        public boolean isOverPower() {
            return overPower;
        }

        public void setOverPower(boolean overPower) {
            this.overPower = overPower;
        }

        public double getPowerTatol() {
            return powerTatol;
        }

        public void setPowerTatol(double powerTatol) {
            this.powerTatol = powerTatol;
        }

        public boolean isUnConnect() {
            return unConnect;
        }

        public void setUnConnect(boolean unConnect) {
            this.unConnect = unConnect;
        }

        public boolean isUserStatus() {
            return userStatus;
        }

        public void setUserStatus(boolean userStatus) {
            this.userStatus = userStatus;
        }
    }
}
