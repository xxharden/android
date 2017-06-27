package com.gqh.mystudio.utill;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2016/12/30.
 */
//selectAllTBytvLatandtvLng
public class HttpUtil {

    public static String openDoor(String sn, String action) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormEncodingBuilder()
                    .add("sn", sn).add("action", action).build();
            Request request = new Request.Builder()
                    .url("http://www.yiliangang.net:8012/ylgPlatform/YunyouLockController/controlLock")
                    .post(formBody).build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
