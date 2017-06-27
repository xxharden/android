package com.gqh.mystudio.bean;

import java.util.List;

/**
 * author:wungko
 * time:16/7/2
 * display:描述这个类
 */
public class ServiceResponseEntity extends BaseEntity {

    /**
     * serviceResult : []
     * serviceParams : [{"paramType":"enum string","paramComment":"测试用的参数","param":"testparam","value":"on,off","valueComment":"开,关"}]
     * serviceId : testinterface
     * serviceName : 测试
     * serviceComment : 用来测试的接口
     */

    public List<DataEntity> data;

    @Override
    public String toString() {
        return "ServiceResponseEntity{" +
                "data=" + data +
                '}';
    }

    public static class DataEntity {
        public String serviceId;
        public String serviceName;
        public String serviceComment;
        public List<?> serviceResult;

        @Override
        public String toString() {
            return "DataEntity{" +
                    "serviceId='" + serviceId + '\'' +
                    ", serviceName='" + serviceName + '\'' +
                    ", serviceComment='" + serviceComment + '\'' +
                    ", serviceResult=" + serviceResult +
                    ", serviceParams=" + serviceParams +
                    '}';
        }

        /**
         * paramType : enum string
         * paramComment : 测试用的参数
         * param : testparam
         * value : on,off
         * valueComment : 开,关
         */

        public List<ServiceParamsEntity> serviceParams;

        public static class ServiceParamsEntity {
            public String paramType;
            public String paramComment;
            public String param;
            public String value;
            public String valueComment;

            @Override
            public String toString() {
                return "ServiceParamsEntity{" +
                        "paramType='" + paramType + '\'' +
                        ", paramComment='" + paramComment + '\'' +
                        ", param='" + param + '\'' +
                        ", value='" + value + '\'' +
                        ", valueComment='" + valueComment + '\'' +
                        '}';
            }
        }
    }
}