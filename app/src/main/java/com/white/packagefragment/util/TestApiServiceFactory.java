package com.white.packagefragment.util;

import retrofit2.Retrofit;

/**
 * Created by MicYun on 2018/1/3.
 */

public class TestApiServiceFactory {

  public static <T> T getApiService(Class<T> tClass) {
    T  apiService;
    Retrofit retrofit = TestRetrofitApiManage.getApiService(TestRetrofitApiManage.BASE_URL);
    apiService = retrofit.create(tClass);
    return apiService;
  }
}
