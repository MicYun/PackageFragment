package com.white.runfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.white.headfotrecyclerview.BaseRecyclerViewAdapter;
import com.white.headfotrecyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.white.runfragment.dataloader.IUpdateDataLoader;
import com.white.runfragment.model.BaseModel;
import com.white.runfragment.presenter.DataUpdatePresenter;
import com.white.runfragment.presenter.IUpdatePresenter;
import com.white.runfragment.view.IUpdateView;

import java.util.List;

public abstract class BaseRecyclerVewFragment<M, Res extends BaseModel<M>> extends AsyncLoadFragment implements IUpdateView<M, Res>{

    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mRefreshLayout;
    protected BaseRecyclerViewAdapter mAdapter;
    protected HeaderAndFooterRecyclerViewAdapter mWrapAdapter;

    protected IUpdatePresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DataUpdatePresenter(getDataLoader());
        mPresenter.onAttachView(this);

    }

    @Override
    protected void onStartLoading() {
        mPresenter.loadData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void onInflated(View contentView, Bundle savedInstanceState) {
        mRecyclerView = contentView.findViewById(R.id.recycler);
        mRefreshLayout = contentView.findViewById(R.id.swipe_refresh);

        mAdapter = newRecyclerViewAdapter();
        mWrapAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);

        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(mWrapAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && isScrollBottom(recyclerView)
                        && mWrapAdapter != null && mWrapAdapter.getItemCount() != 0) {
                    // TODO: 2018/5/7 加载更多逻辑
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    @Override
    public void refreshDataSuc(List<M> data) {

    }

    @Override
    public void refreshDataFail() {

    }

    @Override
    public void loadMoreDataSuc(List<M> data) {

    }

    @Override
    public void loadMoreDataFail() {

    }

    private boolean isScrollBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent()
                + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange()) {
            return true;
        }
        return false;
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract BaseRecyclerViewAdapter newRecyclerViewAdapter();

    protected abstract IUpdateDataLoader getDataLoader();
}
