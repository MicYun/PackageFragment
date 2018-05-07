package com.white.runfragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected View mContentView;

    private boolean isVisible;
    private boolean isPrepared;
    private boolean isLoaded;
    protected boolean isInflated;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isVisible = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutResId(), container, false);
        isPrepared = true;
        return mContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onInflated(view, savedInstanceState);
        isInflated = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkLazyLoad();
    }

    protected void checkLazyLoad() {
        if (isVisible && isPrepared && !isLoaded) {
            loadingData();
            isLoaded = true;
        }
    }

    protected void loadingData() {
        //ViewPage 懒加载数据请求
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void onInflated(View contentView, Bundle savedInstanceState);
}
