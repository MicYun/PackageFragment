package com.white.packagefragment;

import com.white.packagefragment.util.TestApiServiceFactory;
import com.white.runfragment.dataloader.IBaseDataSource;

import io.reactivex.Observable;

public class TestDataSource implements IBaseDataSource<TestModel> {

    private TestApi mApi;

    public TestDataSource() {
        mApi = TestApiServiceFactory.getApiService(TestApi.class);
    }

    @Override
    public Observable<TestModel> refreshData() {
        return mApi.loadTestData(0, 20);
    }

    @Override
    public Observable<TestModel> loadData(int pageNum, int pageSize) {
        return mApi.loadTestData(pageNum, pageSize);
    }

}
