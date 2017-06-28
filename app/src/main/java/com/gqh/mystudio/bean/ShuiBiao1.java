package com.gqh.mystudio.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ShuiBiao1 {
    /**
     * msg : [{"meterId":"1604040197","createTime":"2017-06-21 15:30:05","swicthId":"020202","roomId":"121","model":"WATER","deleted":false,"remark":""}]
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
         * createTime : 2017-06-21 15:30:05
         * swicthId : 020202
         * roomId : 121
         * model : WATER
         * deleted : false
         * remark :
         */

        private String meterId;
        private String createTime;
        private String swicthId;
        private String roomId;
        private String model;
        private boolean deleted;
        private String remark;

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

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
