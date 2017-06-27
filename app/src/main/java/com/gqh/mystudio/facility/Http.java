package com.gqh.mystudio.facility;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author:wungko
 * time:16/6/20
 * display:描述这个类
 */
public class Http {
    private static final String API_URL = "http://123.56.197.113";
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if (retrofit != null) {
            return retrofit;
        } else {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.writeTimeout(30, TimeUnit.SECONDS);
            OkHttpClient client = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }

    }
}