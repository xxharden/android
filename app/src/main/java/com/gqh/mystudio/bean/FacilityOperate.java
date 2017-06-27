package com.gqh.mystudio.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * author:wungko
 * time:16/7/2
 * display:描述这个类
 */
public class FacilityOperate {

    /**
     * thingId : mytv.liupeiyun.bj
     * serviceId : control_test
     * param : {"easy_control":"up"}
     */

    public String thingId;
    public String serviceId;
    public String user;
    public String seq;
    /**
     * easy_control : up
     */

    public Map<String,Object> param;

    public static class ParamEntity {
        public String subscribe;
        public String easy_control;
        public String command;
        public int id;
        public int fan;
        public int line;
        public String user;
    }

    public static class Other{
        /**
         * thingId : mytv.liupeiyun.bj
         * serviceId : control_test
         * param : {"easy_control":"up"}
         */

        public String thingId;
        public String serviceId;
        public String user;
        public String seq;
        /**
         * easy_control : up
         */

        public HashMap<String,Object> param;


    }
}