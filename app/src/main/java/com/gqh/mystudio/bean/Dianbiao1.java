package com.gqh.mystudio.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class Dianbiao1 {
    /**
     * msg : [{"billSn":265,"createTime":"2017-06-21 15:35:42","meterId":"0202010202","swicthId":"020201","roomId":"121","model":"DTSF","multiRate":false,"ct":1,"collectId":130455,"remark":"","deleted":false}]
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
         * billSn : 265
         * createTime : 2017-06-21 15:35:42
         * meterId : 0202010202
         * swicthId : 020201
         * roomId : 121
         * model : DTSF
         * multiRate : false
         * ct : 1
         * collectId : 130455
         * remark :
         * deleted : false
         */

        private int billSn;
        private String createTime;
        private String meterId;
        private String swicthId;
        private String roomId;
        private String model;
        private boolean multiRate;
        private int ct;
        private int collectId;
        private String remark;
        private boolean deleted;

        public int getBillSn() {
            return billSn;
        }

        public void setBillSn(int billSn) {
            this.billSn = billSn;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMeterId() {
            return meterId;
        }

        public void setMeterId(String meterId) {
            this.meterId = meterId;
        }

        public String getSwicthId() {
            return swicthId;
        }

        public void setSwicthId(String swicthId) {
            this.swicthId = swicthId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public boolean isMultiRate() {
            return multiRate;
        }

        public void setMultiRate(boolean multiRate) {
            this.multiRate = multiRate;
        }

        public int getCt() {
            return ct;
        }

        public void setCt(int ct) {
            this.ct = ct;
        }

        public int getCollectId() {
            return collectId;
        }

        public void setCollectId(int collectId) {
            this.collectId = collectId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }
    }
}
