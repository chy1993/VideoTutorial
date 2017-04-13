package com.chy.videotutorial.base.fragment;

import android.os.Bundle;

import com.chy.videotutorial.base.mvp.BasePresenter;


/**
 * Fragment基类：1、加入了友盟的页面统计功能；
 *
 * Created by 丁飞 on 2016/1/7.
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mPresenter;
    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        this.mPresenter.detachView();
        this.mPresenter = null;

        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mPresenter = createPresenter();
    }
}