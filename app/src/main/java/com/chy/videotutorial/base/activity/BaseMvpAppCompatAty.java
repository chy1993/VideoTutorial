package com.chy.videotutorial.base.activity;

import android.os.Bundle;

import com.chy.videotutorial.base.mvp.BasePresenter;


/**
 * Created by 丁飞 on 16/10/7.
 */
public abstract class BaseMvpAppCompatAty<P extends BasePresenter> extends BaseAppCompatActivity {
    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.mPresenter = createPresenter();

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        this.mPresenter.detachView();
        this.mPresenter = null;

        super.onDestroy();
    }
}
