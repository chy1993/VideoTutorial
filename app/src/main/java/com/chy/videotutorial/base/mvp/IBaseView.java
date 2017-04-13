package com.chy.videotutorial.base.mvp;

/**
 * 所有视图接口的基类
 *
 * Created by 丁飞 on 2016/1/7.
 */
public interface IBaseView {
    void onShowErrorView(String msg);
    void onShowLoadingView(String msg);
    void onHideLoadingView();
}