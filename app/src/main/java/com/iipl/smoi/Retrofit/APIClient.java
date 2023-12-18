package com.iipl.smoi.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit retrofit = null;

    public static Retrofit getClient() {

//        String BASE_URL = "https://inboxinfotech.com/silk-mark-india/admin/api/";
//        String BASE_URL = "https://inboxinfotech.com/silk-mark-india/admin/api/";

        String BASE_URL = "https://inboxinfotech.com/silk_mark_india/admin/api/";

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
//                .connectTimeout(200, TimeUnit.SECONDS)
//                .readTimeout(200, TimeUnit.SECONDS)
//                .writeTimeout(200, TimeUnit.SECONDS)
                .build();

//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;

    }

}

