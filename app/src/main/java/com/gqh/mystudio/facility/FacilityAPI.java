package com.gqh.mystudio.facility;

import com.gqh.mystudio.bean.BaseEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * author:wungko
 * time:16/6/20
 * display:设备API
 */
public interface FacilityAPI {
    /**
     * 用户注册设备到物联港
     * @param
     * @return
     */
    @POST("/Service_Platform/pe/appregister.do")
    Call<FacilityRegistResponseEntity> appregister(@Body Facility facility);


    /**
     * 用于查询用户的所有设备列表，以组的形式展示(可以用获取组列表和获取某组下的设备列表两个接口实现)
     * @param userId
     * @return
     */
    @GET("/Service_Platform/group/deviceList.do")
    Call<FacilityRegistResponseEntity> deviceList(@Query("userId") String userId);

    /**
     * 查询设备是否已注册
     * @param /localID
     * @return
     */
    @GET("/Service_Platform/pe/registeredOrNot.do")
    Call<FacilityRegistResponseEntity> registeredOrNot(@Query("localID") String userId);

    /**
     * 用于查询用户某一分组下的设备列表
     * @param userId
     * @return
     */
    @GET("/Service_Platform/group/deviceList.do")
    Call<TypeDeviceListResponseEntity> deviceList(@Query("userId") String userId, @Query("groupId") String groupId);

    /**
     * 用户查询某组下的某类设备列表接口，主要用于能作为智能场景条件的设备列表和能作为一键操控和智能场景操控的设备列表
     * @param type
     * @param userId
     * @param groupId
     * @return
     */
    @GET("/Service_Platform/group/{type}_deviceList.do")
    Call<TypeDeviceListResponseEntity> typeDeviceList(@Path("type") String type, @Query("userId") String userId, @Query("groupId") String groupId);

    /**
     * 设备所有者（注册设备的用户）删除设备
     * @return
     * @param map
     */
    @POST("/Service_Platform/pe/delete.do")
    Call<BaseEntity> delete(@Body DelFacility map);

    class DelFacility{
        public String userId;
        public String thingId;
    }

}
