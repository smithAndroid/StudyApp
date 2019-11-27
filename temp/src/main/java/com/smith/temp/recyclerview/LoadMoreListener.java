package com.smith.temp.recyclerview;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class LoadMoreListener extends RecyclerView.OnScrollListener {
    private int itemCount;
    private int lastItem;
    private boolean isScroll = false;//是否可以滑动
    private RecyclerView.LayoutManager layoutManager;

    /**
     * 加载回调方法
     * @param countItem 总数量
     * @param lastItem  最后显示的position
     */
    protected abstract void onLoading(int countItem, int lastItem);

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        Log.i("smith","---newState--"+newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            isScroll = false;
        } else {
            isScroll = true;
        }

    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            layoutManager = recyclerView.getLayoutManager();
            itemCount = layoutManager.getItemCount();
            lastItem = ((LinearLayoutManager)layoutManager).findLastCompletelyVisibleItemPosition();
        }

        Log.i("smith","---isScroll--"+isScroll+"----itemCount--"+itemCount+"---lastItem--"+lastItem);
        if (isScroll && lastItem == itemCount - 1) {
            onLoading(itemCount,lastItem);
        }


    }
}
