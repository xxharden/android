package com.gqh.mystudio.facility;


import com.gqh.mystudio.bean.ServiceResponseEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * author:wungko
 * time:16/7/1
 * display:描述这个类
 */
public interface FacilityServiceAPI {
    @GET("/Service_Platform/thing/services/{serviceType}.do")
    Call<ServiceResponseEntity> up(@Path("serviceType") String serviceType, @Query("thingId") String thingId, @Query("harborIp") String harborIp);
}