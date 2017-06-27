package com.gqh.mystudio.facility;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;

/**
 * author:wungko
 * time:16/6/20
 * display:用于查询用户的所有设备列表，以组的形式展示(可以用获取组列表和获取某组下的设备列表两个接口实现)
 */
public class DeviceListResponseEntity {
    public List<Data> data;

    public static class Data {
        public String groupName;
        public List<Device> upDevices;
        public List<Device> downDevices;
    }

    public static class Device implements Parcelable {
        public String thingId;
        public String thingName;
        public String templateId;
        public String harborIp;
        public String picUrl;
        public String state;
        public HashMap<String,String> param;

        @Override
        public String toString() {
            return "Device{" +
                    "thingId='" + thingId + '\'' +
                    ", thingName='" + thingName + '\'' +
                    ", templateId='" + templateId + '\'' +
                    ", harborIp='" + harborIp + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ",state='" + state + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.thingId);
            dest.writeString(this.thingName);
            dest.writeString(this.templateId);
            dest.writeString(this.harborIp);
            dest.writeString(this.picUrl);
            dest.writeString(this.state);
        }

        public Device() {
        }

        protected Device(Parcel in) {
            this.thingId = in.readString();
            this.thingName = in.readString();
            this.templateId = in.readString();
            this.harborIp = in.readString();
            this.picUrl = in.readString();
            this.state=in.readString();
        }

        public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() {
            @Override
            public Device createFromParcel(Parcel source) {
                return new Device(source);
            }

            @Override
            public Device[] newArray(int size) {
                return new Device[size];
            }
        };
    }
}