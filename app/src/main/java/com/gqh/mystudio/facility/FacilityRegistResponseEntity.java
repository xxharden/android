package com.gqh.mystudio.facility;

import com.gqh.mystudio.bean.BaseEntity;

/**
 * author:wungko
 * time:16/6/20
 * display:设备注册返回
 */
public class FacilityRegistResponseEntity extends BaseEntity {

    public Data data;


    public static class Data {
        public String peid;
        public String mainHarbor;
        public Harbor mainHarborKey;
        public String spareHarbor;
        public Harbor spareHarborKey;

        public String getPeid() {
            return peid;
        }

        public String getMainHarbor() {
            return mainHarbor;
        }

        public Harbor getMainHarborKey() {
            return mainHarborKey;
        }

        public String getSpareHarbor() {
            return spareHarbor;
        }

        public Harbor getSpareHarborKey() {
            return spareHarborKey;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "peid='" + peid + '\'' +
                    ", mainHarbor='" + mainHarbor + '\'' +
                    ", mainHarborKey=" + mainHarborKey +
                    ", spareHarbor='" + spareHarbor + '\'' +
                    ", spareHarborKey=" + spareHarborKey +
                    '}';
        }
    }


//    peid	string	设备唯一标识
//    mainHarbor	string	主港IP
//    spareHarbor	string	备港IP
//    basekey	string	基础密钥，44字节长度
//    upkey	string	上行密钥，4字节长度
//    downkey	string	下行密钥，4字节长度


    public static class Harbor {
        public String baseKey;
        public String upKey;
        public String downKey;

        @Override
        public String toString() {
            return "Harbor{" +
                    "baseKey='" + baseKey + '\'' +
                    ", upKey='" + upKey + '\'' +
                    ", downKey='" + downKey + '\'' +
                    '}';
        }

        public String getDownKey() {
            return downKey;
        }

        public String getUpKey() {
            return upKey;
        }

        public String getBaseKey() {
            return baseKey;
        }
    }

    @Override
    public String toString() {
        return "FacilityRegistResponseEntity{" +
                "data=" + data +
                '}';
    }

    public Data getData() {
        return data;
    }
}