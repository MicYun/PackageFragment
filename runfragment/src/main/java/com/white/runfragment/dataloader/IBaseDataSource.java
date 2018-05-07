package com.white.runfragment.dataloader;

import io.reactivex.Observable;

public interface IBaseDataSource<R> {

    Observable<R> refreshData();

    Observable<R> loadData(int pageNum, int pageSize);

}
