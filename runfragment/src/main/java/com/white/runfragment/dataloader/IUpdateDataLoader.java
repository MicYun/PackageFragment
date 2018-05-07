package com.white.runfragment.dataloader;

import com.white.runfragment.presenter.IUpdatePresenter;

public interface IUpdateDataLoader<P extends IUpdatePresenter> extends IBaseDataLoader<P> {

    void refreshData();

    void loadData();
}

