package com.white.runfragment.view;

import com.white.runfragment.model.BaseModel;

import java.util.List;

public interface IUpdateView<M, R extends BaseModel<M>> extends IBaseView {

    void refreshDataSuc(List<M> data);

    void refreshDataFail();

    void loadMoreDataSuc(List<M> data);

    void loadMoreDataFail();

    void showLoadingView(String loadTxt);

    void disMissLoadingView();

    void showContent();

    void showToast(String txt);

    void canLoadMore(boolean canLoad);

}
