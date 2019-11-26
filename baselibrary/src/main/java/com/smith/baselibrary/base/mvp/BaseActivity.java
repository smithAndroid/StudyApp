package com.smith.baselibrary.base.mvp;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected P presenter;


}
