package com.smith.baselibrary.base.mvp;

public interface BasePresenter<T extends BaseView> {
    //绑定数据
    void subscribe(T view);
    //解除绑定
    void unSubscribe();
}
