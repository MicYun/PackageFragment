package com.white.runfragment.presenter;

import com.white.runfragment.view.IUpdateView;

public interface IBasePresenter<V extends IUpdateView> {

    void onAttachView(V view);

    void destroyView();

    V getView();
}
