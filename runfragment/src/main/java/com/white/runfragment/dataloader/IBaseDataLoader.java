package com.white.runfragment.dataloader;

import com.white.runfragment.presenter.IBasePresenter;

public interface IBaseDataLoader<P extends IBasePresenter> {
    void bindPresenter(P presenter);

    void unBindPresenter();
}
