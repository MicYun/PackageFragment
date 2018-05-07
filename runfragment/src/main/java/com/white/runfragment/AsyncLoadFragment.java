package com.white.runfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class AsyncLoadFragment extends BaseFragment {

    private boolean allowLoading = true;
    private boolean pendingToLoad = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isInflated) return;

        onPrepareLoading();

        mContentView.post(new Runnable() {
            @Override
            public void run() {
                if (!isAdded()) {
                    return;
                }
                if (allowLoading) {
                    onStartLoading();
                } else {
                    pendingToLoad = true;
                }
            }
        });
    }

    public final void setAllowLoading(boolean allowLoading) {
        this.allowLoading = allowLoading;
        if (allowLoading && pendingToLoad) {
            pendingToLoad = false;
            requestLoad();
        }
    }

    protected final void requestLoad() {
        if (!isInflated) {
            return;
        }
        onPrepareLoading();
        if (allowLoading) {
            onStartLoading();
        } else {
            pendingToLoad = true;
        }
    }

    protected void onPrepareLoading() {}

    protected abstract void onStartLoading();
}
