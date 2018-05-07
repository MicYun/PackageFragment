package com.white.packagefragment;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TestApi {
    @GET("")
    Observable<TestModel> loadTestData(@Query("page") int pageNum, @Query("pageSize") int pageSize);

}
