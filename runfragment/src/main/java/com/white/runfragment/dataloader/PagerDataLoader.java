package com.white.runfragment.dataloader;

import com.white.runfragment.model.BaseModel;
import com.white.runfragment.presenter.DataUpdatePresenter;
import com.white.runfragment.presenter.IUpdatePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class PagerDataLoader<M, R extends BaseModel<M>> implements IUpdateDataLoader<DataUpdatePresenter<M, R>> {

    private static final int PAGE_START_INDEX = 1;
    private static final int PAGE_SIZE = 20;
    private int currentPageNum = PAGE_START_INDEX;

    private IUpdatePresenter mPresenter;
    private IBaseDataSource<R> mDataSource;

    public PagerDataLoader() {
        mDataSource = newDataSource();
    }

    @Override
    public void refreshData() {
        currentPageNum = PAGE_START_INDEX;
        loadData();
    }

    @Override
    public void loadData() {
        mDataSource
                .loadData(currentPageNum, PAGE_SIZE)
                .doOnSubscribe(disposable -> {
                    if (currentPageNum == 1){
                        mPresenter.getView().showLoadingView("数据加载中。。。");}
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        r -> {

                            currentPageNum++;
                            mPresenter.getView().showContent();
                            mPresenter.getView().loadMoreDataSuc(r.getList());
                            canLoadMore(r.getList());

                        },
                        throwable -> {
                            mPresenter.getView().disMissLoadingView();
                            mPresenter.getView().showToast("数据请求失败。");
                        });
    }

    private void canLoadMore(List<M> list) {
        if (list == null || list.size() <= PAGE_SIZE) {
            mPresenter.getView().canLoadMore(false);
        }
    }

    @Override
    public void bindPresenter(DataUpdatePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void unBindPresenter() {
        mPresenter = null;
    }

    protected abstract IBaseDataSource<R> newDataSource();
}
