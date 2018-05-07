package com.white.runfragment.presenter;

import com.white.runfragment.dataloader.IUpdateDataLoader;
import com.white.runfragment.model.BaseModel;
import com.white.runfragment.view.IUpdateView;

public class DataUpdatePresenter<M, R extends BaseModel<M>> implements IUpdatePresenter<IUpdateView<M, R>> {

    private IUpdateView<M, R> mView;
    private IUpdateDataLoader mDataLoader;

    public DataUpdatePresenter(IUpdateDataLoader dataLoader) {
        mDataLoader = dataLoader;
        mDataLoader.bindPresenter(this);
    }

    @Override
    public void refreshData() {
        mDataLoader.refreshData();
    }

    @Override
    public void loadData() {
        mDataLoader.loadData();
    }

    @Override
    public void onAttachView(IUpdateView view) {
        mView = view;
    }

    @Override
    public void destroyView() {
        mView = null;
    }

    @Override
    public IUpdateView getView() {
        return mView;
    }
}
