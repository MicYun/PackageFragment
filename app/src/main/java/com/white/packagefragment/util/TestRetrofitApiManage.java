package com.white.packagefragment.util;

import java.util.WeakHashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MicYun on 2018/1/3.
 */

public class TestRetrofitApiManage {

  public static final String BASE_URL = "https://api.sit.ffan.com/";
  private Retrofit retrofit;
  private static TestRetrofitApiManage manage;
  private WeakHashMap<String, Retrofit> retrofitMap = new WeakHashMap<>();
  private OkHttpClient okHttpClient;

  public TestRetrofitApiManage() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
  }

  private Retrofit getRetrofit(String baseUrl) {
    retrofit = retrofitMap.get(baseUrl);
    if (retrofit == null) {
      retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(okHttpClient)
          .build();
      retrofitMap.put(baseUrl, retrofit);
    }
    return retrofit;
  }

  public static TestRetrofitApiManage getApiManage() {
    if (manage == null) {
      synchronized (TestRetrofitApiManage.class) {
        manage = new TestRetrofitApiManage();
      }
    }
    return manage;
  }

  public static Retrofit getApiService(String baseUrl) {
    return getApiManage().getRetrofit(baseUrl);
  }
}
