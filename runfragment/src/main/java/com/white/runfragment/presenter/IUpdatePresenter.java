package com.white.runfragment.presenter;

import com.white.runfragment.view.IUpdateView;

public interface IUpdatePresenter<V extends IUpdateView> extends IBasePresenter<V> {

    void refreshData();

    void loadData();
}
