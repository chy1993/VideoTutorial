package com.chy.videotutorial.base.mvp;

/**
 * 所有视图接口的基类
 */
public interface IBaseView {
    void onShowErrorView(String msg);
    void onShowLoadingView(String msg);
    void onHideLoadingView();
}